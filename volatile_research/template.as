tell application "IR-FBA"
   -- get names of all the known remotes:
   set rems to remotes
   return rems
end tell

-->  {remote "Apex-RM-1160" of application "IR-FBA", 
remote "JVC-RM-V718U" of application "IR-FBA"}


tell application "IR-FBA"
   -- get the buttons of one remote
   set theButtons to buttons of remote "JVC-RM-V718U"
   return theButtons
end tell

-->  {button "Zoom W" of remote "JVC-RM-V718U" of application "IR-FBA", 
button "Display" of remote "JVC-RM-V718U" of application "IR-FBA", 
button "Fast Forward to End" of remote "JVC-RM-V718U" of application 
"IR-FBA", button "Pause" of remote "JVC-RM-V718U" of application "IR-FBA",
 button "Effect" of remote "JVC-RM-V718U" of application "IR-FBA", button
  "Rewind" of remote "JVC-RM-V718U" of application "IR-FBA"}


tell application "IR-FBA"
   -- send the Pause command to the JVC camcorder
   send button "Pause" of remote "JVC-RM-V718U"
end tell

-->  true 

