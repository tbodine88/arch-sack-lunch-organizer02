#RequireAdmin
#cs
   Index (top of Script)
   - 01TestHeader
   - Version 0
   - Language AutoIt
   - Description
   Template for Tests written in AutoIt

#ce
#include <AssocArrays.au3>

Const $QUIET = 0;
Const $TALKATIVE = 1
const $LOQUACIOUS = 2
Global $verbosity = $LOQUACIOUS
Global $arg[2]

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
