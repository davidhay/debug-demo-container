# Debug Demo Container

### Description
The Spring Boot project is for testing the debug of a docker container.

The Application writes a .txt file to the tmp directory every minute.

Best place to put a debug breakpoint is at first line within `com.container.demo.DemoApplication#writeATempFile`

The container opens 2 ports on the localhost
 * 8282 - for http traffic
 * 6666 - for debugging
### Instructions

```
 cd script
 ./buildImage.sh
 ./startContainerInDebugMode.sh
 ./followLogs.sh
```

At this point you can :
 * open the project in intellij idea
 * add breakpoints - (com.container.demo.DemoApplication#writeATempFile is a good place to start)
 * start a 'remove JVM session' for localhost and port 6666.

#### Misc

* You can check to see if the container is running...

`./checkContainerIsUp.sh`

* You can stop the container is running...

`./stopContainerInDebugMode.sh`

* You can exec into the container - the `*.txt` files will be produced in `/tmp`

`./execIntoContainer.sh`

* You can list the `*.txt` files produced in `/tmp`

`./listTxtFilesInContainerTmp.sh`

* You can copy the `*.txt` files from container's `/tmp` to a local directory

`./copyTxtFilesFromContainer.sh <local-directory>`


