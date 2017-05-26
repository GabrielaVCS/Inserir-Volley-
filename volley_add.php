<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$nome = $_POST['nome'];
		$apelido = $_POST['apelido'];
		$email = $_POST['email'];
		$idade = $_POST['idade'];
		
		include 'conexao.php';
		$sql = "INSERT INTO tb_usuarios_robson (nome,apelido,email,idade) VALUES ('$nome','$apelido','$email','$idade')";
		$inserir = $conex -> prepare($sql);
		if($inserir -> execute()){
			echo 'Cadastrado com sucesso!';
		}else{
			echo 'Erro no cadastro';
		}
 	}else{
 		echo 'Não foi possivel conectar com o banco de dados';
 	}
?>