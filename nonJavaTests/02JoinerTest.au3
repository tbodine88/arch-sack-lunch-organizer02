#RequireAdmin
#cs
   Index (top of Script)
   - 02JoinerDriver
   - Version 0
   - Language AutoIt
   - Description Join the sack lunch with specific user name
   Template for Tests written in AutoIt

#ce
Global $helpMsg = &
"Usage:" & @CRLF & _
@AutoItExe & ' ' &  FileGetShortName(@ScriptFullPath)  & _
"{-f optionFile] " &  _
"{-l login }" & " " & _
"{-p password }" & " " & _
"{-h hint }"& " " & _
"{-n name }"& " " & _
"{-e email }"& " " & _
"{-c [yes|no] }"& " " & _
"{-d [yes|no] }"& " " & _
"{-g [1|2|3|4|5|all] }"& " "

if 0 = $CmdLine[0] Then
   ConsoleWrite($helpMsg)
   exit 1
EndIf

