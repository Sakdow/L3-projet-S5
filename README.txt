=========================================================================== Installation =================================================================================================

1)	Décompresser l’archive
2)	Si WampServer n’est pas installé sur votre machine, le télécharger WAMP sur http://www.wampserver.com/
3)	Mettre adminer.php qui se trouve dans le dossier « Installation », dans le dossier « www » du dossier d’installation de WampServer
4)	Lancer WampServer et attendre que l’icône de WampServer devienne verte
5)	Ouvrir un navigateur Web et rentrer dans la barre d’adresse « localhost »
6)	La page ouverte, dans la section « Vos Alias » cliquer sur adminer
7)	Dans le champ « Utilisateur » mettre le nom d'utilisateur souhaité. Dans le champ << Mot de passe >>, mettre le mot de passe souhaité, puis cliquer sur « Authentification » 
	(par défaut root et pas de mot de passe)
8)	Cliquer sur « créer une base de données », mettre le nom souhaité dans le premier champ. Laisser « (Interclassement) » dans le deuxième et cliquer sur « Enregistrer »
9)	Sur la droite cliquer sur « Requête SQL » et copier le contenu de « script_sql.sql », qui se trouve dans le dossier « installation » dans la zone de texte et cliquer sur Exécuter

======================================================================== Lancer l’application ============================================================================================

1)	Lancer WampServer et attendre que l’icône de WampServer devienne verte
2)	Changer dans le fichier texte « config.txt » les champs « ip », « port », <<adresseBDD>>,  « portBDD », <<nomBDD>>, <<adminBDD>> et <<mdpBDD>> avec respectivement l’ip, le port
	utilisé par le serveur (par défaut « localhost » et « 8090 », correspondant à un serveur qui s’exécute en local), le port utilisé par la base de données que l’on trouve sur la 
	page « localhost » de  « WampServer » sur la version de MySQL (par défaut  « 3306 »), le nom de la base de données défini à l'étape 8) de l'installation, le nom d'utilisateur et
	le mot de passe de la base de données tout deux définis à l'étape 7) de L'installation
3)	Exécuter server.jar et se connecter avec l’identifiant « root » et le mot de passe « root » et attendre que l’application change de fenêtre 
4)	Lancer le(s) client(s) avec client.jar



=============================================================================== Autre ====================================================================================================

-	Les utilisateurs du groupe « Administrateur » peuvent se connecter au serveur
