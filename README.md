#SocialDock

##Install

- Install GlashFish3
- Copy Dependencies from `SDKernel/DepsToDeploy` to `glassfish3\glassfish\domains\domain1\autodeploy\bundles`
- Specify the path to your glassfish bundle directory in the maven parent pom in SocialDock folder

```xml
<properties>
<!-- define the glassfish autodeploy-folder here -->
<gf.autodeploy.path>PATH_TO_GLASSFISH\glassfish3\glassfish\domains\domain1\autodeploy\bundles</gf.autodeploy.path>
<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
```
This will copy all bundles into glassfish autodeploy folder if install phase is executed

- Start Glassfish, build the project and make sure maven install phase was executed

##Access Frontend and Backend

Open browser and navigate to `http://localhost:8080/SocialDock/` for SocialDock-Frontend<br>
Open browser and navigate to `http://localhost:8080/SocialDockAdmin/` for SocialDock-Backend

Note: As long the bundles are in the autodeploy folder you can not disable bundles by the SocialDock-Backend
