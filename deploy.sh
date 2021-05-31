ambiente=$1;

case $ambiente in

	desenvolvimento)

		echo -e "--> Executanto localmente a aplicação"

		mvn clean package -P desenvolvimento

		cd target/

		java -jar ./ApiVendas-0.0.1-SNAPSHOT.jar

		;;

	producao)
	
		echo -e "--> FAZENDO DEPLOY DA APLICAÇÃO NO AMBIENTE DE PRODUCAO"
		
		mvn clean package -P producao

		echo -e "--> AINDA NÃO CAMINHO PARA O ENVIO DA APLICAÇÃO"
		;;

	*)

		usage
		exit 1

		;;

esac

exit 0

