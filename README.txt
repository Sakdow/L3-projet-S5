=========================================================================== Installation =================================================================================================

1)	D�compresser l�archive
2)	Si WampServer n�est pas install� sur votre machine, le t�l�charger WAMP sur http://www.wampserver.com/
3)	Mettre adminer.php qui se trouve dans le dossier � Installation �, dans le dossier � www � du dossier d�installation de WampServer
4)	Lancer WampServer et attendre que l�ic�ne de WampServer devienne verte
5)	Ouvrir un navigateur Web et rentrer dans la barre d�adresse � localhost �
6)	La page ouverte, dans la section � Vos Alias � cliquer sur adminer
7)	Dans le champ � Utilisateur � mettre le nom d'utilisateur souhait�. Dans le champ << Mot de passe >>, mettre le mot de passe souhait�, puis cliquer sur � Authentification � 
	(par d�faut root et pas de mot de passe)
8)	Cliquer sur � cr�er une base de donn�es �, mettre le nom souhait� dans le premier champ. Laisser � (Interclassement) � dans le deuxi�me et cliquer sur � Enregistrer �
9)	Sur la droite cliquer sur � Requ�te SQL � et copier le contenu de � script_sql.sql �, qui se trouve dans le dossier � installation � dans la zone de texte et cliquer sur Ex�cuter

======================================================================== Lancer l�application ============================================================================================

1)	Lancer WampServer et attendre que l�ic�ne de WampServer devienne verte
2)	Changer dans le fichier texte � config.txt � les champs � ip �, � port �, <<adresseBDD>>,  � portBDD �, <<nomBDD>>, <<adminBDD>> et <<mdpBDD>> avec respectivement l�ip, le port
	utilis� par le serveur (par d�faut � localhost � et � 8090 �, correspondant � un serveur qui s�ex�cute en local), le port utilis� par la base de donn�es que l�on trouve sur la 
	page � localhost � de  � WampServer � sur la version de MySQL (par d�faut  � 3306 �), le nom de la base de donn�es d�fini � l'�tape 8) de l'installation, le nom d'utilisateur et
	le mot de passe de la base de donn�es tout deux d�finis � l'�tape 7) de L'installation
3)	Ex�cuter server.jar et se connecter avec l�identifiant � root � et le mot de passe � root � et attendre que l�application change de fen�tre 
4)	Lancer le(s) client(s) avec client.jar



=============================================================================== Autre ====================================================================================================

-	Les utilisateurs du groupe � Administrateur � peuvent se connecter au serveur
