<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuovo evento</title>
    </head>
    <body>
        <h1> Creazione di un nuovo evento </h1>
        <form role="form" action="" method="get" class="login-form">
            <div class="form-group">
                <label class="sr-only">Titolo</label>
                <input type="text" name="Titolo" placeholder="Titolo...">
            </div>
            <div class="form-group">
                <label class="sr-only">Data</label>
                <input type="text" name="Data" placeholder="Data...">
            </div>
            <div class="form-group">
                <label class="sr-only">Programma</label>
                <input type="text" name="Programma" placeholder="Programma..." >
            </div>
            <div class="form-group">
                <label class="sr-only">Descrizione</label>
                <input type="text" name="Descrizione" placeholder="Descrizione...">
            </div>
            <div class="form-group">
                <label class="sr-only">Durata</label>
                <input type="text" name="Durata" placeholder="Durata...">
            </div>
            <div class="form-group">
                <label class="sr-only">Sponsor</label>
                <input type="text" name="Sponsor" placeholder="Sponsor...">
            </div>
            <div class="form-group">
                <label class="sr-only">Social 1</label>
                <input type="text" name="Social1" placeholder="Social 1...">
            </div>
            <div class="form-group">
                <label class="sr-only">Social 2</label>
                <input type="text" name="Social2" placeholder="Social 2...">
            </div>
            <div class="form-group">
                <label class="sr-only">Biglietti</label>
                <input type="text" name="Biglietti" placeholder="Biglietti...">
            </div>
            <button type="submit" class="btn">Conferma</button>	
            <button type="submit" class="btn">Annulla</button>	
        </form>
   
    </body>
</html>
