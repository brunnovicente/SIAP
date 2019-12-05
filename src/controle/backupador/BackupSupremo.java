package controle.backupador;

import controle.FachadaControle;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Thiago on 10/1/2015.
 */
public class BackupSupremo {
    private String mysql;
    private String mysqldump;
    private String user;
    private String password;
    private String database;
    private String filename;
    private Process p;

    public BackupSupremo() {
        mysqldump = "mysqldump";
        this.user = "root";
        this.password = "";
        this.database = "mysql";
    }

    public BackupSupremo(String user, String password, String database) {
        mysqldump = "mysql";
        mysqldump = "mysqldump";
        this.user = user;
        this.password = password;
        this.database = database;
    }

    public BackupSupremo(String mysql, String mysqldump, String user, String password, String database) {
        this.mysql = mysql;
        this.mysqldump = mysqldump;
        this.user = user;
        this.password = password;
        this.database = database;
    }

    public Process getBackupProcess() {
        return p;
    }

    public boolean fazerBackup(String filename) {
        try {
            //FachadaControle.getFachadaControle().Fechar();
            DateFormat dateFormat = new SimpleDateFormat("-yyyy.MM.dd-HH.mm.ss");
            Date date = new Date();
            String fullfilename = filename + dateFormat.format(date) + ".sql";

            File file = new File(fullfilename);
            file.getParentFile().mkdirs();
            file.createNewFile();
            System.out.println("MYSQL: "+mysql);
            System.out.println("MYSQL DUMP: "+mysqldump);
            ProcessBuilder process = new ProcessBuilder("cmd.exe", "/c", mysqldump, "--user=" + user, "--password=" + password, database, "-r", fullfilename);
            p = process.start();
            System.out.println(">>>>>>>>COMANDO:  " + p.toString());
            Thread t = new Thread(new ProcessThread(p));
            t.start();
        } catch (IOException ex) {
            return false;
        }

        return true;
    }

    public boolean recuperarBackup(String filename) {
        FachadaControle.getFachadaControle().Fechar();
        try {
            File file = new File(filename);

            ProcessBuilder process = new ProcessBuilder("cmd.exe", "/c", mysql, "--user=" + user, "--password=" + password, database, "<", file.getAbsolutePath());
            p = process.start();
            System.out.println(">>>>>>>>COMANDO:  " + p.toString());
            Thread t = new Thread(new ProcessThread(p));
            t.start();
        } catch (IOException ex) {
            Logger.getLogger(BackupSupremo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    private Process start(String comando) {
        try {
            return Runtime.getRuntime().exec(comando);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public synchronized static String execCommand(final String commandLine) throws IOException {

        boolean success = false;
        String result;

        Process p;
        BufferedReader input;
        StringBuffer cmdOut = new StringBuffer();
        String lineOut = null;
        int numberOfOutline = 0;

        try {

            p = Runtime.getRuntime().exec(commandLine);

            input = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((lineOut = input.readLine()) != null) {
                if (numberOfOutline > 0) {
                    cmdOut.append("\n");
                }
                cmdOut.append(lineOut);
                numberOfOutline++;
            }

            result = cmdOut.toString();

            success = true;

            input.close();

        } catch (IOException e) {
            result = String.format("Falha ao executar comando %s. Erro: %s", commandLine, e.toString());
        }

        // Se não executou com sucesso, lança a falha
        if (!success) {
            throw new IOException(result);
        }

        return result;

    }

    class ProcessThread implements Runnable {
        private Process p;

        public ProcessThread(Process p) {
            this.p = p;
        }

        @Override
        public void run() {
            try {
                while(p.isAlive()) {
                    Thread.sleep(1000);
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                System.err.println("Saida=" + p.exitValue() + " Mensagem=" + sb.toString());
            } catch (IOException ex) {
                Logger.getLogger(BackupSupremo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(BackupSupremo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
