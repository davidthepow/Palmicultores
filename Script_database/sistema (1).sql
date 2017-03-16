-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-12-2016 a las 08:12:47
-- Versión del servidor: 10.1.19-MariaDB
-- Versión de PHP: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistema`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ag_user`
--

CREATE TABLE `ag_user` (
  `IDagregar` bigint(20) UNSIGNED NOT NULL,
  `usuarios` text NOT NULL,
  `contra` text NOT NULL,
  `nombre` text NOT NULL,
  `a_pater` text NOT NULL,
  `a_mater` text NOT NULL,
  `edad` tinyint(3) NOT NULL,
  `telefono` bigint(13) NOT NULL,
  `sexo` text CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `correo` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ag_user`
--

INSERT INTO `ag_user` (`IDagregar`, `usuarios`, `contra`, `nombre`, `a_pater`, `a_mater`, `edad`, `telefono`, `sexo`, `correo`) VALUES
(2, 'David', 'Link', 'Erik David', 'Avila', 'Cruz', 22, 9821034055, 'Masculino', 'davidthepow1@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `IDnombre` int(1) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `A_paterno` text NOT NULL,
  `A_materno` text NOT NULL,
  `D_calle` text NOT NULL,
  `D_col` text NOT NULL,
  `D_numero` int(100) NOT NULL,
  `C_postal` int(100) NOT NULL,
  `Estado` text NOT NULL,
  `C_electronico` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `IDprod` int(10) UNSIGNED NOT NULL,
  `proveedor` text NOT NULL,
  `producto` text NOT NULL,
  `origen` text NOT NULL,
  `destino` text NOT NULL,
  `entrada` int(11) NOT NULL,
  `folio` int(11) NOT NULL,
  `pesokg` bigint(20) NOT NULL,
  `fechaentrada` date NOT NULL,
  `estatus` text NOT NULL,
  `descripcion` text NOT NULL,
  `salida` int(11) NOT NULL,
  `pesobruto` bigint(20) NOT NULL,
  `pesotara` bigint(20) NOT NULL,
  `pesoneto` bigint(20) NOT NULL,
  `fechasalida` date NOT NULL,
  `hora` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`IDprod`, `proveedor`, `producto`, `origen`, `destino`, `entrada`, `folio`, `pesokg`, `fechaentrada`, `estatus`, `descripcion`, `salida`, `pesobruto`, `pesotara`, `pesoneto`, `fechasalida`, `hora`) VALUES
(16, 'ARTURO SUÁREZ AMÉNDOLA', 'FRUTA DE PALMA DE ACEITE', 'PLAN DE AYALA', 'PLANTA EXTRACTORA DE ACEITE', 502, 2052, 4320, '2016-12-08', 'Completo', '', 502, 4320, 2570, 1750, '2016-12-08', '15:46:28'),
(17, 'ARTURO SUÁREZ AMÉNDOLA', 'FRUTA DE PALMA DE ACEITE', 'PLAN DE AYALA', 'PLANTA EXTRACTORA DE ACEITE', 503, 2053, 5600, '2016-12-08', 'Completo', '', 503, 5600, 1200, 4400, '2016-12-08', '21:53:29'),
(18, 'FRANCISCO BALLINA', 'FRUTA DE PALMA DE ACEITE', '18 DE MARZO', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 504, 2054, 3464, '2016-12-09', 'Completo', '', 504, 366, 234, 132, '2016-12-09', '00:42:48'),
(19, 'CAROLINA NOVELO CAN ', 'FRUTA DE PALMA DE ACEITE', 'MAMANTEL', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 505, 2055, 2523, '2016-12-09', 'Completo', '', 505, 5234, 2344, 2890, '2016-12-09', '00:45:55'),
(20, 'OSCAR FRANCISCO MARROQUÍN', 'FRUTA DE PALMA DE ACEITE', 'ESCARCEGA', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 506, 2056, 2523, '2016-12-09', 'Completo', '', 506, 4223, 2332, 1891, '2016-12-09', '00:47:35'),
(21, 'JORGE CHABLE JIMENEZ', 'FRUTA DE PALMA DE ACEITE', 'ESCARCEGA', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 507, 2057, 2324, '2016-12-09', 'Completo', '', 507, 3635, 2345, 1290, '2016-12-09', '00:48:44'),
(22, 'OSCAR DAMAS MARTINEZ', 'FRUTA DE PALMA DE ACEITE', 'MAMANTEL', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 508, 2058, 3563, '2016-12-09', 'Completo', '', 508, 3466, 2455, 1011, '2016-12-09', '00:50:00'),
(23, 'IGNACIO HERANANDEZ JIMENEZ', 'FRUTA DE PALMA DE ACEITE', 'HARO', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 509, 2059, 3663, '2016-12-09', 'Completo', '', 509, 3445, 2324, 1121, '2016-12-09', '00:51:14'),
(24, 'ARTURO SUÁREZ AMÉNDOLA', 'FRUTA DE PALMA DE ACEITE ', 'PLAN DE AYALA', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 510, 2060, 4438, '2016-12-09', 'Completo', '', 510, 3465, 2323, 1142, '2016-12-09', '00:53:31'),
(25, 'FRANCISCO BALLINA', 'FRUTA DE PALMA DE ACEITE', '18 DE MARZO', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 511, 2060, 4845, '2016-12-09', 'Completo', '', 511, 8383, 4743, 3640, '2016-12-09', '00:55:19'),
(26, 'CAROLINA NOVELO CAN', 'FRUTA DE PALMA DE ACEITE', 'MAMANTEL', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 512, 2061, 3442, '2016-12-09', 'Completo', '', 512, 3454, 2334, 1120, '2016-12-09', '00:56:31'),
(27, 'OSCAR FRANCISCO MARROQUÍN', 'FRUTA DE PALMA DE ACEITE', 'ESCARCEGA', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 513, 2062, 5352, '2016-12-09', 'Completo', '', 513, 4534, 2343, 2191, '2016-12-09', '00:57:50'),
(28, 'JORGE CHABLE JIMENEZ', 'FRUTA DE PALMA DE ACEITE', 'ESCARCEGA', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 514, 2063, 4522, '2016-12-09', 'Completo', '', 514, 2454, 1263, 1191, '2016-12-09', '00:59:01'),
(29, 'OSCAR DAMAS MARTINEZ', 'FRUTA DE PALMA DE ACEITE', 'MAMANTEL', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 515, 2064, 4344, '2016-12-09', 'Completo', '', 515, 4224, 3243, 981, '2016-12-09', '01:01:38'),
(30, 'IGNACIO HERANANDEZ JIMENEZ', 'FRUTA DE PALMA DE ACEITE', 'HARO', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 516, 2065, 2523, '2016-12-09', 'Completo', '', 516, 7435, 3445, 3990, '2016-12-09', '01:03:06'),
(31, 'FRANCISCO BALLINA', 'FRUTA DE PALMA DE ACEITE', 'MAMANTEL', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 517, 2066, 2352, '2016-12-09', 'Completo', '', 517, 6543, 4532, 2011, '2016-12-09', '01:04:15'),
(32, 'OSCAR FRANCISCO MARROQUÍN', 'FRUTA DE PALMA DE ACEITE', 'ESCARCEGA', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 518, 2066, 3632, '2016-12-09', 'Completo', '', 518, 6332, 3456, 2876, '2016-12-09', '01:05:16'),
(33, 'OSCAR DAMAS MARTINEZ', 'FRUTA DE PALMA DE ACEITE', 'MAMANTEL', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 519, 2067, 1354, '2016-12-09', 'Completo', '', 519, 5453, 3525, 1928, '2016-12-09', '01:06:31'),
(34, 'IGNACIO HERANANDEZ JIMENEZ', 'FRUTA DE PALMA DE ACEITE', 'HARO', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 520, 2068, 2522, '2016-12-09', 'Completo', '', 520, 2525, 1232, 1293, '2016-12-09', '01:07:17'),
(35, 'ARTURO SUÁREZ AMÉNDOLA', 'FRUTA DE PALMA DE ACEITE', 'PLAN DE AYALA', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 521, 2068, 3463, '2016-12-09', 'Completo', '', 521, 3535, 2344, 1191, '2016-12-09', '01:08:13'),
(36, 'JORGE CHABLE JIMENEZ', 'FRUTA DE PALMA DE ACEITE', 'ESCARCEGA', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 522, 2069, 2334, '2016-12-09', 'Completo', '', 522, 9323, 4522, 4801, '2016-12-09', '01:09:07'),
(37, 'OSCAR FRANCISCO MARROQUÍN', 'FRUTA DE PALMA DE ACEITE', 'ESCARCEGA', 'Planta Extractora de Aceite ''Don Jorge Mena Pérez''', 523, 2070, 3346, '2016-12-09', 'Completo', '', 523, 3456, 2214, 1242, '2016-12-09', '01:10:15');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `ID_usuarios` bigint(20) UNSIGNED NOT NULL,
  `usuario` varchar(255) NOT NULL,
  `clave` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`ID_usuarios`, `usuario`, `clave`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `IDventa` int(255) NOT NULL,
  `f_entra` date NOT NULL,
  `nom_p` text NOT NULL,
  `cod` int(255) NOT NULL,
  `can` int(255) NOT NULL,
  `precio` int(255) NOT NULL,
  `subt` bigint(20) NOT NULL,
  `iva` bigint(20) NOT NULL,
  `t_total` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`IDventa`, `f_entra`, `nom_p`, `cod`, `can`, `precio`, `subt`, `iva`, `t_total`) VALUES
(10, '2016-12-07', 'ppp', 222, 2, 300, 0, 0, 0),
(11, '2016-12-07', 'hihgl', 545, 4, 654654, 64, 21, 3151),
(12, '2016-12-07', 'aceite crudo', 1234, 2, 1256, 5332, 23, 25658);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ag_user`
--
ALTER TABLE `ag_user`
  ADD PRIMARY KEY (`IDagregar`),
  ADD UNIQUE KEY `IDagregar` (`IDagregar`),
  ADD UNIQUE KEY `IDagregar_2` (`IDagregar`),
  ADD UNIQUE KEY `IDagregar_3` (`IDagregar`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`Nombre`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`IDprod`),
  ADD UNIQUE KEY `IDProducto` (`IDprod`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`ID_usuarios`),
  ADD UNIQUE KEY `ID_usuarios` (`ID_usuarios`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`IDventa`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ag_user`
--
ALTER TABLE `ag_user`
  MODIFY `IDagregar` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `IDprod` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `ID_usuarios` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `IDventa` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
