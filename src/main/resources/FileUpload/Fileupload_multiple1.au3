Local $hWnd = 0
Switch $CmdLine[1]
	Case "CHROME"
		$hWnd = WinWait("Open","", 10)
	Case "FIREFOX"
		$hWnd = WinWait("File Upload","", 10)
	Case "IE"
		$hWnd = WinWait("Choose File to Upload","", 10)
EndSwitch
Local $sFileNames = ''
Local $nIndex = 0
For $nIndex = 2 To $CmdLine[0]
	$sFileNames &= '"' & $CmdLine[$nIndex] & '" '
Next
$sFileNames = StringStripWS ($sFileNames, 2)
ConsoleWrite("value of file name ="&$sFileNames)
ControlFocus($hWnd, "", "Edit1")
ControlSetText($hWnd, "", "Edit1", $sFileNames)
ControlClick($hWnd, "", "Button1")