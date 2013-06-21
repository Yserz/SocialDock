#SocialDock

##Installation

- Install GlashFish3
- Copy Dependencies from `SDKernel/DepsToDeploy` to `glassfish3\glassfish\domains\domain1\autodeploy\bundles`
- Specify the path to your glassfish bundle directory in the maven parent pom in SocialDock folder

```xml
<properties>
<!-- define the glassfish autodeploy-folder here -->
<gf.autodeploy.path>PATH_TO_GLASSFISH\glassfish3\glassfish\domains\domain1\autodeploy\bundles</gf.autodeploy.path>
<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
```

- Start Glassfish, build the project and make sure maven install phase was executed
