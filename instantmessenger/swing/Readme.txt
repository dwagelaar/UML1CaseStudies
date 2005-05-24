Instant Messenger - Swing Version
=================================

This version was derived from InstantMessengerModel.xmi
through the following operations:

* IMAWT.launch
* IMSwing.launch
* IMJabber.launch
* IMSMS.launch
* IMJava2Attributes.launch
* IMJava2Accessors.launch
* IMJavaObserver.launch
* IMSingleton.launch
* IMApplet.launch
* IMJava2DataTypes.launch
* ./forOutput.sh InstantMessengerDataTypes.xmi > InstantMessengerOutput.xmi
* Code is generated from InstantMessengerOutput.xmi with Poseidon CE 3.0.1
* im.view.lcdui package is deleted afterwards

Note that the Swing version also includes the AWT view; it defaults to the
Swing view, but if that fails it falls back to the AWT view. As such, this
version also runs on J2ME Personal Profile.