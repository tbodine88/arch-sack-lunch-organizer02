#include <MsgBoxConstants.au3>


#region --- Au3Recorder generated code Start (v3.3.9.5 KeyboardLayout=00000409)  ---

#region --- Internal functions Au3Recorder Start ---
Func _Au3RecordSetup()
Opt('WinWaitDelay',100)
Opt('WinDetectHiddenText',1)
Opt('MouseCoordMode',0)
opt('WinTitleMatchMode',2)
opt('WinTextMatchMode',2)
Local $aResult = DllCall('User32.dll', 'int', 'GetKeyboardLayoutNameW', 'wstr', '')
If $aResult[1] <> '00000409' Then
  MsgBox(64, 'Warning', 'Recording has been done under a different Keyboard layout' & @CRLF & '(00000409->' & $aResult[1] & ')')
EndIf

EndFunc

Func _WinWaitActivate($title,$text,$timeout=0)
	WinWait($title,$text,$timeout)
	If Not WinActive($title,$text) Then WinActivate($title,$text)
	WinWaitActive($title,$text,$timeout)
EndFunc

_AU3RecordSetup()
#endregion --- Internal functions Au3Recorder End ---

if ( not WinExists("Welcome to","") ) Then

ShellExecute("http://localhost:8080/arch-sack-lunch-organizer02/")

EndIf
_WinWaitActivate("Welcome ","")
MouseClick("left",1107,158,1)
_WinWaitActivate("Join","")
MouseClick("left",366,214,1)
Send("WildLogin{TAB}")
Send("Apassword{TAB}")
Send("Apassword{TAB}")
Send("A Hint is made here{TAB}")
Send("My Name Goes Here{TAB}")
Send("2223334544{TAB}")
send("anemail@address.here{tab}");
send ("{SPACE}{TAB}{SPACE}{TAB}");
send ("{SPACE}{TAB}{SPACE}{TAB}");
send("{ENTER}");
sleeP(2000)
If WinExists("Login","") or WinExists("password in use","") Then
      MsgBox($MB_OK,"Passed","valid Login page")
	  exit( 0)
else
   MsgBox($MB_OK,"Failed","Invalid page")
   exit(1)
EndIf

#endregion --- Au3Recorder generated code End ---
