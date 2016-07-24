/**
 * Display incomming values from Ableton Live
 *
 * Needs OSCLive from http://livecontrol.q3f.org/ableton-liveapi/liveosc/
 * to be installed within Ableton
 * complete List of OSC calls at http://monome.q3f.org/browser/trunk/LiveOSC/OSCAPI.txt
 * 
 * GPL Licensed 
 * by Hartmut habu Wöhlbier * MAD Lab - Mannheim Digital Lab / Hochschule Mannheim
 * http://bit.ly/G_habu
 * Please do not remove this header.
 */

import oscP5.*;
import netP5.*;

OscP5 oscP5;
NetAddress myRemoteLocation;

void setup() {
  size(400,400);
  // start oscP5, listening for incoming messages at port 9001 from Ableton */
  oscP5 = new OscP5(this,9001);
  myRemoteLocation = new NetAddress("127.0.0.1",9000); // loopback to Ableton port 9000
}

void draw() {
}

void oscEvent(OscMessage theOscMessage) {
  // what is comming in?
  println("### received an osc message with typtag: " + theOscMessage.typetag() + ", Length: " + theOscMessage.typetag().length());
  
  print("Pattern " + theOscMessage.addrPattern() + " with values: ");
  for(int i=0; i<theOscMessage.typetag().length(); i++)
  {  switch(theOscMessage.typetag().charAt(i))
       {  case 'i':  print(theOscMessage.get(i).intValue() + " ");
                     break;
          case 'f':  print(theOscMessage.get(i).floatValue() + " ");
                     break;
          case 's':  print(theOscMessage.get(i).stringValue() + " ");
                     break;
       }
  }
  print("\n"); 
  return;
}
