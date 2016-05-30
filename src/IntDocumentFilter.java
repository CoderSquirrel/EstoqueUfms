
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
     
 public class IntDocumentFilter extends DocumentFilter {    
     
    public IntDocumentFilter() {    
     }    
     
     public void insertString(FilterBypass fb, int offs,    
                              String str, AttributeSet a)    
         throws BadLocationException {    
         
         for( int i = 0; i < str.length(); i++ )    
                     if( Character.isDigit( str.charAt( i ) ) == false )    
                         return;        
             fb.insertString(offs, str, a);  
               
               
               
     }    
         
     public void replace(FilterBypass fb, int offs,    
                         int length,     
                         String str, AttributeSet a)    
         throws BadLocationException {    
          
             for( int i = 0; i < str.length(); i++ )    
                     if( Character.isDigit( str.charAt( i ) ) == false )    
                         return;        
             fb.replace(offs, length, str, a);  
         }    
     }