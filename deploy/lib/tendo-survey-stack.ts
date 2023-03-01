import * as cdk from 'aws-cdk-lib';
import * as ecr from 'aws-cdk-lib/aws-ecr';
import * as ec2 from "aws-cdk-lib/aws-ec2";
import * as ecs from "aws-cdk-lib/aws-ecs";
import * as rds from 'aws-cdk-lib/aws-rds';
import * as ecs_patterns from 'aws-cdk-lib/aws-ecs-patterns';
import { Construct } from 'constructs';

export class TendoSurveyStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const imageRepository = new ecr.Repository(this, 'tendo-survey-repo');

    const vpc = new ec2.Vpc(this, 'survey-vpc', {
      maxAzs: 2,
      ipAddresses: ec2.IpAddresses.cidr('10.0.0.0/24')
    });

    const db = new rds.DatabaseInstance(this, 'survey-db', {
      engine: rds.DatabaseInstanceEngine.postgres({ version: rds.PostgresEngineVersion.VER_14_5 }),
      instanceType: ec2.InstanceType.of(ec2.InstanceClass.BURSTABLE3, ec2.InstanceSize.SMALL),
      vpc,
      vpcSubnets: {
        subnetType: ec2.SubnetType.PUBLIC,
      },
      publiclyAccessible: true
    });
    db.connections.allowFromAnyIpv4(ec2.Port.allTraffic(), 'Allow all traffic');

    const cluster = new ecs.Cluster(this, 'survey-cluster', {
      vpc: vpc
    });

    new ecs_patterns.ApplicationLoadBalancedFargateService(this, 'survey-service', {
      cluster: cluster,
      cpu: 512,
      desiredCount: 1,
      taskImageOptions: {
        image: ecs.ContainerImage.fromEcrRepository(imageRepository, 'latest'),
        containerPort: 8080
      },
      memoryLimitMiB: 1024,
      publicLoadBalancer: true,
      listenerPort: 8080
    });
  }
}
