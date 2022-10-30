-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 21, 2021 at 05:30 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `m-banking`
--
CREATE DATABASE IF NOT EXISTS `m-banking` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `m-banking`;

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `idKategori` varchar(20) NOT NULL,
  `namaKategori` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`idKategori`, `namaKategori`) VALUES
('K01', 'Withdraw'),
('K02', 'Deposit'),
('K03', 'Transfer Money');

-- --------------------------------------------------------

--
-- Table structure for table `rekening`
--

CREATE TABLE `rekening` (
  `noRekening` int(20) NOT NULL,
  `saldo` int(255) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rekening`
--

INSERT INTO `rekening` (`noRekening`, `saldo`, `date`) VALUES
(1, 390000, '2021-11-01'),
(2, 6410000, '2021-11-01');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `noTransaksi` int(255) NOT NULL,
  `noRekening` int(20) NOT NULL,
  `idKategori` varchar(20) NOT NULL,
  `idUser` varchar(20) NOT NULL,
  `noRekTujuan` varchar(11) DEFAULT NULL,
  `nominal` varchar(50) NOT NULL,
  `Date` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`noTransaksi`, `noRekening`, `idKategori`, `idUser`, `noRekTujuan`, `nominal`, `Date`) VALUES
(8, 1, 'K01', '2', NULL, '123000', '2021-11-20'),
(53, 2, 'K02', '2', NULL, '100000', '2021-11-21'),
(54, 1, 'K02', '1', NULL, '100000', '2021-11-21'),
(55, 1, 'K03', '1', '2', '100000', '2021-11-21'),
(56, 1, 'K01', '1', NULL, '100000', '2021-11-21'),
(57, 1, 'K02', '1', NULL, '300000', '2021-11-21'),
(58, 1, 'K01', '1', NULL, '50000', '2021-11-21'),
(59, 1, 'K01', '1', NULL, '50000', '2021-11-21'),
(60, 1, 'K01', '1', NULL, '50000', '2021-11-21'),
(61, 1, 'K03', '1', '2', '100000', '2021-11-21'),
(62, 1, 'K01', '1', NULL, '10000', '2021-11-21'),
(63, 1, 'K01', '1', NULL, '40000', '2021-11-21'),
(64, 1, 'K02', '1', NULL, '50000', '2021-11-21'),
(65, 1, 'K01', '1', NULL, '50000', '2021-11-21'),
(66, 1, 'K02', '1', NULL, '100000', '2021-11-21'),
(67, 1, 'K03', '1', '2', '10000', '2021-11-21');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `idUser` int(20) NOT NULL,
  `noRekening` varchar(20) NOT NULL,
  `fullName` varchar(35) NOT NULL,
  `userName` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `pin` varchar(10) NOT NULL,
  `phoneNumber` varchar(15) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`idUser`, `noRekening`, `fullName`, `userName`, `password`, `pin`, `phoneNumber`, `email`, `address`) VALUES
(1, '1', 'John Doe', 'john', '123123', '123456', '08132323232', 'johndoe1@yahoo.com', 'Jakarta'),
(2, '2', 'Andreas Silaen ', 'andreas', '123123', '123456', NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`idKategori`);

--
-- Indexes for table `rekening`
--
ALTER TABLE `rekening`
  ADD PRIMARY KEY (`noRekening`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`noTransaksi`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `rekening`
--
ALTER TABLE `rekening`
  MODIFY `noRekening` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `noTransaksi` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
