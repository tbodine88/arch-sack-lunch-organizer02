#include <MsgBoxConstants.au3>
Global $groupToSelect = "2,4";
Global $groupToChoose = "2";
Const $QUIET = 0;
Const $TALKATIVE = 1
const $LOQUACIOUS = 2
Global $verbosity = $LOQUACIOUS
HotKeySet("!q","quit")

Func quit()
   exit 0
EndFunc

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

$login = StringSplit("Alogin2b Blogin2b Clogin2b Elogin2b Flogin2b Glogin2b"," ")
$pass ="APassword"
$name = StringSplit("Able Beans,Babble Cake,Calling Tree,Elegant Halloway,Float 2bad,Gloming Galvin",",")
$phone = StringSplit("18832243564,333-444-2453,232-875-4343,1-234-564-7786,3-453-994-5432,3-453-994-5432,3-453-994-5432",",")
$email = StringSplit("here@there.com,She@he.there,fred@flintsone.roc,wally@trolley.com,walk@run.com",",")
$canDeliver = StringSplit("yes yes yes no yes"," ")
$committes = StringSplit("1 2 3 4 1,3"," ")
$permission = StringSplit("MEMBER ADMINISTRATOR MEMBER FACILITATOR MEMBER"," ")
$hint = StringSplit("This is a beautiful:Girls have fun:dogs have bones:snow is cold:humpty one is there:Lost in thought",":")
_WinWaitActivate("Welcome ","",10)
MouseClick("left",1263,220,1)
_WinWaitActivate("Sack Lunch ","",5)
MouseClick("left",111,198,1)
Send("administrator{TAB}javapass")
MouseClick("left",51,243,1)
Sleep(1000)
send("{tab}{Enter}")
for $ix = 1 to UBound($login) - 1
sleep(1000)
MouseMove(571,235,10) ; goto Name
MouseDown("main");
MouseUp("main");

Send($name[$ix]& "{TAB}");
Send($phone[$ix]& "{TAB}")
send($email[$ix]& "{TAB}")
send($canDeliver[$ix]& "{TAB}")
send($committes[$ix] & "{TAB}")
send($Permission[$ix] & "{TAB}")
send($login[$ix] & "{TAB}")
send($pass &"{TAB}")
send($hint[$ix] & "{TAB}")
send("{TAB 5}")
Notify("Ready",3)
MouseClick("main",435,416,10)



_WinWaitActivate("Sack Lunch ","",5)
Next

$title = WinGetTitle("Chrome")
if ( StringInStr($title,"Member Menu") )Then
   Notify("Pass--> Member Menu Shown",4)
   exit(0)
Else
   Notify("Fail--> Member Menu Not Shown",4)
EndIf





#endregion --- Au3Recorder generated code End ---
