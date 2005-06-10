Not all jar files are stored in the subversion repository,
because they belong to the ATL eclipse engine plugin.
Check the .classpath file one directory up for the necessary
jar files and copy them here.

When using the command line interface within Ant (without fork),
then "xercesImpl.jar" also needs to be added to the classpath.