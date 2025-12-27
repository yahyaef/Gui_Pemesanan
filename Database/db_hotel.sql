-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 01 Jan 2023 pada 08.32
-- Versi server: 10.4.20-MariaDB
-- Versi PHP: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_hotel`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `datapesan`
--

CREATE TABLE `datapesan` (
  `kode_transaksi` int(10) NOT NULL,
  `NIK` int(15) NOT NULL,
  `Nama` varchar(20) NOT NULL,
  `JenisKel` varchar(10) NOT NULL,
  `Alamat` varchar(20) NOT NULL,
  `Telp` char(15) NOT NULL,
  `NoKamar` int(10) NOT NULL,
  `TipeKamar` varchar(15) NOT NULL,
  `Harga` int(15) DEFAULT NULL,
  `Lamanginap` char(5) NOT NULL,
  `Total` int(15) DEFAULT NULL,
  `tglmenginap` varchar(10) DEFAULT NULL,
  `Bayar` int(15) DEFAULT NULL,
  `Kembalian` int(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `datapesan`
--

INSERT INTO `datapesan` (`kode_transaksi`, `NIK`, `Nama`, `JenisKel`, `Alamat`, `Telp`, `NoKamar`, `TipeKamar`, `Harga`, `Lamanginap`, `Total`, `tglmenginap`, `Bayar`, `Kembalian`) VALUES
(21131225, 2113020072, 'Yahya Eko Febrianto', 'Pria', 'Rejoso', '087856432348', 8, 'Deluxe Room', 2000000, '2', 4000000, '2023-01-26', 5000000, 1000000),
(21131327, 2113020056, 'Deni Umar Wirawan', 'Pria', 'Tanjunganom', '082278654321', 11, 'Deluxe Room', 2000000, '2', 4000000, '2024-01-18', 4500000, 500000),
(21131329, 2113020103, 'Alfaiz Putra Nugroho', 'Pria', 'Kertosono', '085867897654', 15, 'Standard Room', 500000, '3', 1500000, '2023-01-26', 2000000, 500000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `identitas`
--

CREATE TABLE `identitas` (
  `Nik` int(15) NOT NULL,
  `Nama` varchar(20) NOT NULL,
  `JenisKel` varchar(10) NOT NULL,
  `Alamat` varchar(20) NOT NULL,
  `Telp` char(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `identitas`
--

INSERT INTO `identitas` (`Nik`, `Nama`, `JenisKel`, `Alamat`, `Telp`) VALUES
(2113020056, 'Deni Umar Wirawan', 'Pria', 'Tanjunganom', '082278654321'),
(2113020072, 'Yahya Eko Febrianto', 'Pria', 'Rejoso', '087856432348'),
(2113020103, 'Alfaiz Putra Nugroho', 'Pria', 'Kertosono', '085867897654');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kamar`
--

CREATE TABLE `kamar` (
  `NoKamar` int(15) NOT NULL,
  `TipeKamar` varchar(15) NOT NULL,
  `Harga` int(15) DEFAULT NULL,
  `Lamanginap` char(5) NOT NULL,
  `Total` int(15) DEFAULT NULL,
  `tglmenginap` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `kamar`
--

INSERT INTO `kamar` (`NoKamar`, `TipeKamar`, `Harga`, `Lamanginap`, `Total`, `tglmenginap`) VALUES
(8, 'Deluxe Room', 2000000, '2', 4000000, '2023-01-26'),
(11, 'Deluxe Room', 2000000, '2', 4000000, '2024-01-18'),
(15, 'Standard Room', 500000, '3', 1500000, '2023-01-26');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `datapesan`
--
ALTER TABLE `datapesan`
  ADD PRIMARY KEY (`kode_transaksi`);

--
-- Indeks untuk tabel `identitas`
--
ALTER TABLE `identitas`
  ADD PRIMARY KEY (`Nik`);

--
-- Indeks untuk tabel `kamar`
--
ALTER TABLE `kamar`
  ADD PRIMARY KEY (`NoKamar`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
