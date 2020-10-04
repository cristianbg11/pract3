<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado de estudiantes</title>
    <link href="css/stylesheet.css" type="text/css" rel="stylesheet">
</head>
<body>

<header id="header">
    <nav class="links" style="--items: 5;">
        <a href="/">Inicio</a>
        <a href="/crear">Crear</a>
        <a href="#">Login</a>
        <a href="#">Sign out</a>
        <span class="line"></span>
    </nav>
</header>

<div class="container">
    <table>
        <thead>
        <tr>
            <th>Matricula</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Telefono</th>
        </tr>
        </thead>
        <tbody>
        <#if listado?has_content>
            <#list listado as estudiante>
                <tr>
                    <td>${estudiante.matricula?c}</td>
                    <td>${estudiante.nombre}</td>
                    <td>${estudiante.apellido}</td>
                    <td>${estudiante.telefono}</td>
                    <td id='select'>
                        <a id='button' href='/view/${estudiante?index}'>
                            Ver
                        </a>
                        <a id='button' href='/edit/${estudiante?index}'>
                            Editar
                        </a>
                        <a id='button' href='/delete/${estudiante?index}'>
                            Eliminar
                        </a>
                    </td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>

</body>
</html>