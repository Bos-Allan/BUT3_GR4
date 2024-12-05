-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : devbdd.iutmetz.univ-lorraine.fr
-- Généré le : jeu. 05 déc. 2024 à 15:24
-- Version du serveur : 10.3.39-MariaDB
-- Version de PHP : 8.2.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bosquet9u_bankiut`
--

-- --------------------------------------------------------

--
-- Structure de la table `Compte`
--

CREATE TABLE `Compte` (
  `numeroCompte` varchar(50) NOT NULL,
  `userId` varchar(50) NOT NULL,
  `solde` double NOT NULL,
  `avecDecouvert` varchar(5) NOT NULL,
  `decouvertAutorise` decimal(10,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `Compte`
--

INSERT INTO `Compte` (`numeroCompte`, `userId`, `solde`, `avecDecouvert`, `decouvertAutorise`) VALUES
('AB7328887341', 'client2', -97, 'AVEC', 123),
('BD4242424242', 'client1', 230, 'SANS', NULL),
('FF5050500202', 'client1', 705, 'SANS', NULL),
('IO1010010001', 'client2', 6868, 'SANS', NULL),
('LA1021931215', 'client1', 150, 'SANS', NULL),
('MD8694030938', 'client1', 70, 'SANS', NULL),
('PP1285735733', 'a', 37, 'SANS', NULL),
('TD0398455576', 'client2', 34, 'AVEC', 700),
('XD1829451029', 'client2', -93, 'AVEC', 100),
('XX7788778877', 'client2', 90, 'SANS', NULL),
('XX9999999999', 'client2', 0, 'AVEC', 500);

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
  `userId` varchar(50) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `userPwd` varchar(255) DEFAULT NULL,
  `male` bit(1) NOT NULL,
  `type` varchar(10) NOT NULL,
  `numClient` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`userId`, `nom`, `prenom`, `adresse`, `userPwd`, `male`, `type`, `numClient`) VALUES
('a', 'a', 'a', 'a', '1f40fc92da241694750979ee6cf582f2d5d7d28e18335de05abc54d0560e0f5302860c652bf08d560252aa5e74210546f369fbbbce8c12cfc7957b2652fe9a75', b'1', 'MANAGER', NULL),
('admin', 'Smith', 'Joe', '123, grande rue, Metz', '836bc6397d06de5f635683cff01822564683b57c5298c38bd389628685d9ce9d74cba952fc80ac305a6dd1d122bb041dfa93377880d478f27b99da3fafc05bf6', b'1', 'MANAGER', ''),
('allan', 'allan', 'allan', '40 rue des mac', 'd404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db', b'1', 'MANAGER', NULL),
('client1', 'client1', 'Jane', '45, grand boulevard, Brest', '76d46c8a83c0259e23db4705c2d0aa3c29e66c0ea5e7aeb9e356bd86d97d39ca2b4cbb0a3c7d09a59638fb2bf565e4e95405289ea25086233895281d4af4da13', b'1', 'CLIENT', '123456789'),
('client2', 'client2', 'Jane', '45, grand boulevard, Brest', '293c9f92b6b55b2860868c8a74ee7e06524bade691ea3393eacf9f2ee21afd515532b8a8a5839aa906d357f3dc7bb7a05163724410ddc6f1685614da29309ad0', b'1', 'CLIENT', '123456788');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Compte`
--
ALTER TABLE `Compte`
  ADD PRIMARY KEY (`numeroCompte`),
  ADD KEY `index_userClient` (`userId`);

--
-- Index pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `numClient_UNIQUE` (`numClient`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Compte`
--
ALTER TABLE `Compte`
  ADD CONSTRAINT `fk_Compte_userId` FOREIGN KEY (`userId`) REFERENCES `Utilisateur` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
