#!/bin/bash
for f in ./*/*/SD*-SNAPSHOT.*
do 
   cp -v $f /Applications/NetBeans/glassfish-3.1.2.2/glassfish/domains/domain1/autodeploy/bundles 
done
