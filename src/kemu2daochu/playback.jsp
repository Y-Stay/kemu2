<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
	<meta http-equiv="Expires" content="0" /> 
   <script src="jquery-1.7.1.min.js"></script>
<script src="webVideoCtrl.js"></script>
<script src="videoserver.js"></script>
	<script>
	    function f(){
	    	
	    var //szIP="10.61.100.164";
	        szIP="192.168.15.10";
	    	szPort =80,
			szUsername = "admin",
			szPassword = "klzx12345"; 
	    	iChannelID =<%
	    			
	    			int channel=Integer.parseInt(request.getAttribute("channel").toString());
	    	       out.println(channel);
	    	%>,
	    			
			szStartTime = "<%
			       String startTime=(String)request.getAttribute("startTime");
	    	       out.print(startTime);
			%>",
			szEndTime = "<%
			       String endTime=(String)request.getAttribute("endTime");
 	       out.print(endTime);
		%>",
	
	    clickLogin(szIP,szPort,szUsername,szPassword,iChannelID,szStartTime,szEndTime);
	    }
	</script>
</head>
 <body onload=f()>
 
  <div id="divPlugin" class="plugin" align="center"></div>
<br>
<div align="center">
                    <input type="button" class="btn2" value="开始" onclick="f()"/>
					<input type="button" class="btn2" value="停止" onclick="clickStopPlayback();" />
					<input type="button" class="btn" value="倒放" onclick="clickReversePlayback();" />
					<input type="button" class="btn" value="暂停" onclick="clickPause();" />
					<input type="button" class="btn" value="恢复" onclick="clickResume();" />
					<input type="button" class="btn" value="慢放" onclick="clickPlaySlow();" />
					<input type="button" class="btn" value="快进" onclick="clickPlayFast();" />
					<input type="button" class="btn" value="声音开" onclick="clickOpenSound();" />
		</div>
 </body>

</html>
