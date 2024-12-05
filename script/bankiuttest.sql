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
-- Base de données : `bosquet9u_bankiuttest`
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
('AB7328887341', 'j.doe2', 4242, 'AVEC', 123),
('AV1011011011', 'g.descomptes', 5, 'AVEC', 100),
('BD4242424242', 'j.doe1', 100, 'SANS', NULL),
('CADNV00000', 'j.doe1', 42, 'AVEC', 42),
('CADV000000', 'j.doe1', 0, 'AVEC', 42),
('CSDNV00000', 'j.doe1', 42, 'SANS', NULL),
('CSDV000000', 'j.doe1', 0, 'SANS', NULL),
('IO1010010001', 'j.doe2', 6868, 'SANS', NULL),
('KL4589219196', 'g.descomptesvides', 0, 'AVEC', 150),
('KO7845154956', 'g.descomptesvides', 0, 'SANS', NULL),
('LA1021931215', 'j.doe1', 100, 'SANS', NULL),
('MD8694030938', 'j.doe1', 500, 'SANS', NULL),
('PP1285735733', 'a.lidell1', 37, 'SANS', NULL),
('SA1011011011', 'g.descomptes', 10, 'SANS', 0),
('TD0398455576', 'j.doe1', 23, 'AVEC', 500),
('XD1829451029', 'j.doe1', -48, 'AVEC', 100);

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
  `userId` varchar(50) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `userPwd` varchar(200) DEFAULT NULL,
  `male` bit(1) NOT NULL,
  `type` varchar(10) NOT NULL,
  `numClient` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`userId`, `nom`, `prenom`, `adresse`, `userPwd`, `male`, `type`, `numClient`) VALUES
('a.lidell1', 'Lidell', 'Alice', '789, grande rue, Metz', '10e06b990d44de0091a2113fd95c92fc905166af147aa7632639c41aa7f26b1620c47443813c605b924c05591c161ecc35944fc69c4433a49d10fc6b04a33611', b'1', 'CLIENT', '9865432100'),
('admin', 'Smith', 'Joe', '123, grande rue, Metz', '5285f4666f6d2dd6de6886d088a1ce1a4b816c77fda55405afac60a085dbb79451ac1fb99309a3bf9352edd869998a633fab545085b9cf0fdbb003c3de5a50db', b'1', 'MANAGER', ''),
('c.exist', 'TEST NOM', 'TEST PRENOM', 'TEST ADRESSE', '85b16fda866fc5b5a41367051f36f8d195e5c409bfedffa06077b663c579a805e7276efc1b6510b0225a6af6bc39e18a8248bc1b881add22cbd2391cba810b31', b'1', 'CLIENT', '0101010101'),
('g.descomptes', 'TEST NOM', 'TEST PRENOM', 'TEST ADRESSE', '85b16fda866fc5b5a41367051f36f8d195e5c409bfedffa06077b663c579a805e7276efc1b6510b0225a6af6bc39e18a8248bc1b881add22cbd2391cba810b31', b'1', 'CLIENT', '1000000001'),
('g.descomptesvides', 'TEST NOM', 'TEST PRENOM', 'TEST ADRESSE', '85b16fda866fc5b5a41367051f36f8d195e5c409bfedffa06077b663c579a805e7276efc1b6510b0225a6af6bc39e18a8248bc1b881add22cbd2391cba810b31', b'1', 'CLIENT', '0000000002'),
('g.exist', 'TEST NOM', 'TEST PRENOM', 'TEST ADRESSE', '85b16fda866fc5b5a41367051f36f8d195e5c409bfedffa06077b663c579a805e7276efc1b6510b0225a6af6bc39e18a8248bc1b881add22cbd2391cba810b31', b'1', 'CLIENT', '1010101010'),
('g.pasdecompte', 'TEST NOM', 'TEST PRENOM', 'TEST ADRESSE', '85b16fda866fc5b5a41367051f36f8d195e5c409bfedffa06077b663c579a805e7276efc1b6510b0225a6af6bc39e18a8248bc1b881add22cbd2391cba810b31', b'1', 'CLIENT', '5544554455'),
('j.doe1', 'Doe', 'Jane', '456, grand boulevard, Brest', '10e06b990d44de0091a2113fd95c92fc905166af147aa7632639c41aa7f26b1620c47443813c605b924c05591c161ecc35944fc69c4433a49d10fc6b04a33611', b'1', 'CLIENT', '1234567890'),
('j.doe2', 'Doe', 'John', '457, grand boulevard, Perpignan', '10e06b990d44de0091a2113fd95c92fc905166af147aa7632639c41aa7f26b1620c47443813c605b924c05591c161ecc35944fc69c4433a49d10fc6b04a33611', b'1', 'CLIENT', '0000000001');

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
