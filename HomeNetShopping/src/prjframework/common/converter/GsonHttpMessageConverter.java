package prjframework.common.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class GsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    private Gson gson = new Gson();

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    
    private Type type = null; 
    
    private boolean prefixJson = false; 

    public GsonHttpMessageConverter(){
        super(new MediaType("application", "json", DEFAULT_CHARSET));
    }
    
    public void setType( Type type ){ 
        this.type = type; 
    } 
 
    public Type getType(){ 
        return type; 
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz,
                                  HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
    	
        try{
            return gson.fromJson(convertStreamToString(inputMessage.getBody()), clazz);
        }catch(JsonSyntaxException e){
            throw new HttpMessageNotReadableException("Could not read JSON: " + e.getMessage(), e);
        }

    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    /*
    @Override
    protected void writeInternal(Object t, 
                                 HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        //TODO: adapt this to be able to receive a list of json objects too

        String json = gson.toJson(t);

        outputMessage.getBody().write(json.getBytes());
    }
    */
    @Override 
    protected void writeInternal( Object o, HttpOutputMessage outputMessage ) 
            throws IOException, HttpMessageNotWritableException{ 
 
        OutputStreamWriter writer = new OutputStreamWriter( outputMessage.getBody(), getCharset( outputMessage.getHeaders() ) ); 
 
        try{ 
            if( this.prefixJson ){ 
                writer.append( "{} && " ); 
            } 
            Type typeOfSrc = getType(); 
            if( typeOfSrc != null ){ 
                this.gson.toJson( o, typeOfSrc, writer ); 
            } 
            else{ 
                this.gson.toJson( o, writer ); 
            } 
            writer.close(); 
        } 
        catch( JsonIOException ex ){ 
            throw new HttpMessageNotWritableException( "Could not write JSON: " + ex.getMessage(), ex ); 
        } 
    } 

    //TODO: move this to a more appropriated utils class
    public String convertStreamToString(InputStream is) throws IOException {
        /*
         * To convert the InputStream to String we use the Reader.read(char[]
         * buffer) method. We iterate until the Reader return -1 which means
         * there's no more data to read. We use the StringWriter class to
         * produce the string.
         */
        if (is != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }

    private Charset getCharset( HttpHeaders headers ){ 
        if( headers != null && headers.getContentType() != null 
                && headers.getContentType().getCharSet() != null ){ 
            return headers.getContentType().getCharSet(); 
        } 
        return DEFAULT_CHARSET; 
    } 
}
