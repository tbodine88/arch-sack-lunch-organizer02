#include <MsgBoxConstants.au3>
Global $groupToSelect = "2,4";
Global $groupToChoose = "2";
Const $QUIET = 0;
Const $TALKATIVE = 1
const $LOQUACIOUS = 2
Global $verbosity = $LOQUACIOUS


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
Func Notify( $message, $wait=5, $title="Notice" )
   if $verbosity > $QUIET then ConsoleWrite( $message & @CRLF)
   if $verbosity > $TALKATIVE then   MsgBox($MB_OK,$title,$message,$wait)
EndFunc
Func FindRadioButton($centerX, $centerY,$look4Title)
	_WinWaitActivate($look4Title,"",20)
	MouseMove($centerX, $centerY)
	$centerX = MouseGetPos(0)
	$centerY = MouseGetPos(1)
	Local $offset = 1
	While WinExists($look4Title, "")
		_WinWaitActivate($look4Title,"",20)
		MouseClick("main", $centerX + $offset, $centerY)
		If not WinExists($look4Title, "") Then ExitLoop
		MouseClick("main", $centerX, $centerY + $offset)
		If not WinExists($look4Title, "") Then ExitLoop
		MouseClick("main", $centerX + $offset, $centerY + $offset)
		If not WinExists($look4Title, "") Then ExitLoop
		MouseClick("main", $centerX - $offset, $centerY)
		If not WinExists($look4Title, "") Then ExitLoop
		MouseClick("main", $centerX, $centerY - $offset)
		If not WinExists($look4Title, "") Then ExitLoop
		MouseClick("main", $centerX + $offset, $centerY - $offset)
		$offset = $offset * 2
		If $offset > 16 Then
			if ($goNext) then
				return "None"
			 EndIf
			SplashTextOn($name,"Select Radio Button,",500,100,10,10)
			while WinExists($look4Title, "")
				sleep(100)
			wend
			SplashOff()
			ExitLoop
		EndIf
	 WEnd
  EndFunc

for $i =1 to $CmdLine[0]
   if (StringInStr($CmdLine[$i],"-h") > 0) then
	  $help = "AllGoodTwoGroup - Tests Arch sack lunch organizer " & @CRLF & _
	  "  usage:" & @CRLF & _
	  "    AllGoodTwoGroup [-h] [-g 1,[2,[3,[4,[5,]]]] [-s 1|2|3|4|5 ] [-q[q]] " & @CRLF  & _
	  @LF & "  -h[elp]  this message " & @CRLF & _
	  "    -g[roup] {list of groups}   select these groups to join" & @CRLF  & _
	  "    -s[elect] {group} select this group after login" & @CRLF  & _
	  "    -q         Don't show popups" & @CRLF  & _
	  "    -qq  No popups or console writes" & @CRLF
	  Notify($help)
	  exit(1)
   ElseIf (StringInStr($CmdLine[$i],"-g") > 0 ) Then
	  $groupToSelect = $CmdLine[$i + 1 ]
   ElseIf(StringInStr($CmdLine[$i],"-s") > 0) Then
	  $groupToChoose = $CmdLine[$i+1]
   ElseIf(StringInStr($CmdLine[$i],"-qq") > 0 )  Then
	  $verbosity = $QUIET
   ElseIf(StringInStr($CmdLine[$i],"-q" )> 0 ) Then
	  $verbosity = $TALKATIVE
   Else
     ConsoleWrite("skip " & $CmdLine[$i])
  EndIf
Next




if ( not WinExists("Welcome to","") ) Then

ShellExecute("http://localhost:8080/arch-sack-lunch-organizer02/")

EndIf

$login = "LaxBug2b"
$pass ="APassword"
_WinWaitActivate("Welcome ","")
MouseClick("left",1107,158,1)
_WinWaitActivate("Join","")
MouseClick("left",366,214,1)
Send($login &"{TAB}")
Send($pass & "{TAB}")
Send($pass & "{TAB}")
Send("A Hint to be a word said passionately{TAB}")
Send("Lovely Lady{TAB}")
Send("222-333-4534{TAB}")
send("anxmail@address.here{tab}");
send ("{SPACE}{TAB}"); car
send ("{SPACE}{TAB}"); license
if ( StringInStr($groupToSelect,"1" ) > 0 ) then send("{SPACE}")
send ("{TAB}"); first monday
if ( StringInStr($groupToSelect, "2") > 0 ) then send("{SPACE}")
send ("{TAB}"); second monday
if ( StringInStr($groupToSelect, "3") > 0 ) then send("{SPACE}")
send ("{TAB}"); third
if ( StringInStr($groupToSelect, "4" ) > 0 ) then send("{SPACE}")
send ("{TAB}"); fourth
if ( StringInStr($groupToSelect, "5" ) > 0 ) then send("{SPACE}")
send ("{TAB}"); fifth
send("{ENTER}");
sleep(2000)
$title = WinGetTitle("Chrome")
Notify("Title after form submission: " & $title )
If StringInStr($title,"Login")  Then
      Notify("Passed valid Login page")
elseif StringInStr($title, "password in use") Then
   ShellExecute("http://localhost:8080/arch-sack-lunch-organizer02/go")
else
   Notify("Failed Invalid page")
   exit(1)
EndIf
if ( _WinWaitActivate("Sack Lunch Login","",10) = 0 ) Then
   Notify("Failed Invalid page")
   exit(1)
EndIf
send("{TAB}" &$login & "{TAB}");
send($pass & "{TAB}{ENTER}")
sleep(2000)
$title = WinGetTitle("Chrome")
Notify("Title after log in submission: " & $title  )
If ( StringInStr($title,"Choose The Meeting") ) Then
   MsgBox($MB_OK,"Passed","valid Group Chooser page",5)
Else
   MsgBox($MB_OK,"Failed","Invalid Group Chooser page",5)
   exit(1)
EndIf
if ( $groupToChoose = "1" ) then
   findRadioButton(32,244,"Choose The Meeting")
ElseIf ( $groupToChoose = "2" ) then
   findRadioButton(139,246,"Choose The Meeting")
ElseIf ( $groupToChoose = "3" ) then
   findRadioButton(251,249,"Choose The Meeting")
ElseIf ( $groupToChoose = "4" ) then
   findRadioButton(365,249,"Choose The Meeting")
ElseIf ( $groupToChoose = "5" ) then
   findRadioButton(472,246,"Choose The Meeting")
else
   Notify( "groupToChoose " & $groupToChoose & "not recognized",5)
EndIf
$title = WinGetTitle("Chrome")
if ( StringInStr($title,"Member Menu") Then
   Notify("Pass--> Member Menu Shown",4)
   exit(0)
Else
   Notify("Fail--> Member Menu Not Shown",4)
EndIf





#endregion --- Au3Recorder generated code End ---
