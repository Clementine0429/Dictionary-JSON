<?php
	require_once('lib/db.php');
	if(isset($_GET['Status'])){
		$r=DP::run_query('SELECT `word`, `definition` FROM `table_dictionary` WHERE `Status`=?',[$_GET['Status']],2);
		echo json_encode($r);
	}
	
	if(isset($_GET['word'])&& isset($_GET['definition'])){
		$r=DP::run_query('insert into table_dictionary(`word`, `definition`) values (?, ?)', [$_GET['word'], $_GET['definition']], 3);
		echo json_encode($r);
	}
?>
