
package teastorehouse.utils;

import org.apache.commons.lang3.math.NumberUtils;


public class Validators {
    

    
    /**
     * Validation method which checks if given string is parsable into number.
     * @param string single string from various userinputs.
     * @return True if string is empty or not empty & parsable. 
     *         False if not empty & not parsable.
     */
    public boolean validateEntryNumberOrEmpty(String string) {
        if (!string.isEmpty() && !NumberUtils.isParsable(string)) {
            return false;
        }
        return true;
    }
}
