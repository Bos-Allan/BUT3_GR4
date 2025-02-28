## QualDev

## Information sur Sprint 3

 -Rajout de tests selenium 
- Rajout de plus de tests avec JUnit et Mockito
- Modification du build github action pour ignorer les tests selenium afin de ne pas bloquer le build car sinon il faut lancer le serveur tomcat avant de réaliser les tests 

- Coverage après tests réalisée fin sprint 3 :
  ![image](https://github.com/user-attachments/assets/fb500fd7-5e78-499b-ae19-a35d65b01710)



Membres du groupe :
- Alexis Caron
- Allan Bosquet
- Maxime Bonnet

Bugs Corrigés : 
- Bug n°1 bloquant : Tout le monde peut accéder aux pages admin. (Par exemple a http://localhost:8081/_00_ASBank2023/listeCompteManager.action?aDecouvert=false => Réglé en ajoutant une intercepteur qui vérifie si l’utilisateur est connecté, et s’il est un Manager. Dans le cas négatif, l’utilisateur est envoyé à la page de connexion.
- Bug n°2 bloquant : Action Débite qui crédite de l'argent => Correction : Transformation du form qui avec pour action  creditActionEdit en 2 forms qui pour l'un permet de créditer de l'argent et pour l'autre d'en débiter
- Bug n°1 non bloquant: Logo IUT qui ne s'affiche pas => Correction du lien de l'image qui n'existe plus sur le site de l'IUT par une image existante.
- Bug n°2 non bloquant: Icon pour l'édition de compte qui se s'affiche pas => Correction du chemin de l'image qui n'existe plus sur "freeflaticons.com" par un icon existant à la place

Coverage début : 

![image](https://github.com/user-attachments/assets/a7bc8a16-b1c4-4a9d-b0de-c0c744e7ee60)

Coverage après tests réalisée par Allan : 

![image](https://github.com/user-attachments/assets/439fcf17-33df-457b-ba3b-837ac70602cd)
