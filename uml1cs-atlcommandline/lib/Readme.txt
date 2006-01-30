Not all jar files are stored in the subversion repository,
because they belong to the ATL eclipse engine plugin.
If you have the ATL plugin installed, you may run the
copydep.build.xml script to automatically find and copy
the jar dependencies. Alternatively, check the .classpath
file one directory up for the necessary jar files and copy
them here.

When using the command line interface within Ant (without fork),
then "xercesImpl.jar" also needs to be added to the classpath.