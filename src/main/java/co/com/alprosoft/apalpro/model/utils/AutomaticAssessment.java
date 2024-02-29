/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.alprosoft.apalpro.model.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author leand
 */
public class AutomaticAssessment {
    
    private static final String AUTOMATIC_ASSESSMENT_MAIN_DIRECTORY_ROUTE = "C:\\Users\\leand\\Documents\\NetBeansProjects\\apalpro\\assessments";
    
    private static final String AUTOMATIC_ASSESSMENT_PYTHON_3_DEFAULT_FILE_NAME = "main.py"; 

    public AutomaticAssessment() {
    }
    
    public static boolean findIfFileExists () {
        return false;
    }
    
    public static boolean createPython3File (String code, int user_id) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter fileWriter = new PrintWriter(AUTOMATIC_ASSESSMENT_MAIN_DIRECTORY_ROUTE + "\\" + user_id 
                + "\\" + AUTOMATIC_ASSESSMENT_PYTHON_3_DEFAULT_FILE_NAME, "UTF-8");
        // Separa la cadena de caracteres (código Python 3) por la existencia de saltos de línea (\n).
        // De esto, resulta un conjunto de cadena de caracteres, una cadena de caracter por línea.
        String lines[] = code.split("\\r?\\n|\\r");
        for (String line : lines) {
            fileWriter.println(line);
        }
        fileWriter.close();
        return false;
    }
    
    public static boolean deleteFile () {
        return false;
    }
    
    public static boolean assessmentMainDirectoryExists () {
        File f = new File(AUTOMATIC_ASSESSMENT_MAIN_DIRECTORY_ROUTE);
        return f.isDirectory(); // Esta función también revisa si el directorio existe o no.           
    }
    
    public static boolean createAssessmentMainDirectory () {
      // Instanciar la clase File 
      File assessmentDirectoryRoute = new File(AUTOMATIC_ASSESSMENT_MAIN_DIRECTORY_ROUTE);  
      // Crear un folder usando el método mkdir()
      return assessmentDirectoryRoute.mkdir();
    }
    
    public static boolean assessmentUserDirectoryExists (int user_id) {
        File f = new File(AUTOMATIC_ASSESSMENT_MAIN_DIRECTORY_ROUTE + "\\" + user_id);
        return f.isDirectory(); // Esta función también revisa si el directorio existe o no.           
    }
    
    public static boolean createAssessmentUserDirectory (int user_id) {
      // Instanciar la clase File 
      File assessmentDirectoryRoute = new File(AUTOMATIC_ASSESSMENT_MAIN_DIRECTORY_ROUTE + "\\" + user_id);  
      // Crear un folder usando el método mkdir()
      return assessmentDirectoryRoute.mkdir();
    }
    
    public static boolean deleteDirectory () {
        return false;
    }
    
    public static String[] executePython3Program (String input_data, int user_id) throws IOException {
        // {stdOutput, stdError}
        String[] stdMsgs = new String[2];
        
        Runtime rt = Runtime.getRuntime();
        String[] commands = {"python3 " + "'" + AUTOMATIC_ASSESSMENT_MAIN_DIRECTORY_ROUTE + "\\" + user_id 
                + "\\" + AUTOMATIC_ASSESSMENT_PYTHON_3_DEFAULT_FILE_NAME + "'"};
        Process proc = rt.exec(commands);
        
        // By default, the subprocess reads input from a pipe. Java code can access this pipe via the output stream returned by Process.getOutputStream().
        BufferedWriter stdOutputStreamWriter = new BufferedWriter(new OutputStreamWriter(proc.getOutputStream()));
        
        // By default, the subprocess writes standard output and standard error to pipes. 
        // Java code can access these pipes via the input streams returned by Process.getInputStream() and Process.getErrorStream(). 
        // However, standard output and standard error may be redirected to other destinations using redirectOutput and redirectError.
        BufferedReader stdInputStreamReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        BufferedReader stdErrorStreamReader = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        // Ingresa la entrada (input) que necesita el programa asociado al comando (subproceso) para funcionar.
        // Lee la salida (output) y errores (error) estándar que devuelve el comando.
        String s = null;
        
        // Ingresa la entrada (input) estándar que necesita el programa para funcionar.
        stdOutputStreamWriter.flush();
        String input_data_lines[] = input_data.split("\\r?\\n|\\r");
        for (String line: input_data_lines) {
            stdOutputStreamWriter.write(line, 0, line.length());
        }
        
        // Lee la salida (output) del comando
        String stdOutput = null;        
        while ((s = stdInputStreamReader.readLine()) != null) {
            if (stdOutput == null) {
                stdOutput = s;
            } else {
                stdOutput += s;
            }
        }
        
        // Lee los errores del comando intentado (Es posible que no haya errores)
        String stdError = null;
        while ((s = stdErrorStreamReader.readLine()) != null) {
            if (stdError == null) {
                stdError = s;
            } else {
                stdError += s;
            }
        }
        
        if (stdOutput != null) {
            stdMsgs[0] = stdOutput;
        }
        if (stdError != null) {
            stdMsgs[1] = stdError;
        }
        
        stdOutputStreamWriter.close();
        stdInputStreamReader.close();
        stdErrorStreamReader.close();
        
        return null;
    }
    
    public static boolean assessPython3Program () {
        return false;
    }
    
}
