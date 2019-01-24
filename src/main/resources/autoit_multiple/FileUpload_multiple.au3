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
ControlSetText($hWnd,"","Edit1","C:\Users\suruchi.das\Documents\FEDEX2.docx")
;ControlSetText("[CLASS:#32770]","","Edit1",'"C:\Users\suruchi.das\Documents\FEDEX_TESTING.docx" "C:\Users\suruchi.das\Documents\FEDEX2.docx"')
ControlClick($hWnd,"","Button1")