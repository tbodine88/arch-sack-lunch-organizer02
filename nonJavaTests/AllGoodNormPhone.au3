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
	Return WinWaitActive($title,$text,$timeout)
EndFunc

_AU3RecordSetup()
#endregion --- Internal functions Au3Recorder End ---

if ( not WinExists("Welcome to","") ) Then

ShellExecute("http://localhost:8080/arch-sack-lunch-organizer02/")

EndIf

$login = "WildLogin"
$pass ="APassword"
_WinWaitActivate("Welcome ","")
MouseClick("left",1107,158,1)
_WinWaitActivate("Join","")
MouseClick("left",366,214,1)
Send($login &"{TAB}")
Send($pass & "{TAB}")
Send($pass & "{TAB}")
Send("A Hint is made here{TAB}")
Send("My Name Goes Here{TAB}")
Send("222-333-4544{TAB}")
send("anemail@address.here{tab}");
send ("{SPACE}{TAB}"); car
send ("{SPACE}{TAB}"); license
send ("{TAB}"); first monday
send ("{SPACE}{TAB}"); second monday
send ("{TAB}"); third
send ("{TAB}"); fourth
send ("{TAB}"); fifth
send("{ENTER}");
sleep(2000)
$title = WinGetTitle("Chrome")
ConsoleWrite( $title & @CRLF )
If StringInStr($title,"Login")  Then
      MsgBox($MB_OK,"Passed","valid Login page",5)
elseif StringInStr($title, "password in use") Then
   ShellExecute("http://localhost:8080/arch-sack-lunch-organizer02/go")
else
   MsgBox($MB_OK,"Failed","Invalid page")
   exit(1)
EndIf
if ( _WinWaitActivate("Sack Lunch Login","",10) = 0 ) Then
   MsgBox($MB_OK,"Failed","Invalid page")
EndIf

send("{TAB}" &$login & "{TAB}");
send($pass & "{TAB}{ENTER}")





#endregion --- Au3Recorder generated code End ---
