Local $hWnd = 0
Switch $CmdLine[1]
	Case "CHROME"
		$hWnd = WinWait("Open","", 10)
	Case "FIREFOX"
		$hWnd = WinWait("File Upload","", 10)
	Case "IE"
		$hWnd = WinWait("Choose File to Upload","", 10)
EndSwitch
ControlFocus($hWnd,"","Edit1")
ControlSend($hWnd,"","Edit1",$CmdLine[2])
ControlClick($hWnd,"","Button1")