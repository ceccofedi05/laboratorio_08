package it.unibo.deathnote;

import org.junit.jupiter.api.Test;
import it.unibo.deathnote.impl.DeathNoteimpl;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import static it.unibo.deathnote.api.DeathNote.RULES;
import it.unibo.deathnote.api.DeathNote;
import static java.lang.Thread.sleep;

class TestDeathNote {
/* Write a test for the `DeathNote` implementation (the test will fail and that is okay) testing the following: */

  final String Lamine_Yamal = "Lamine Yamal";
  final String Lionel_Messi = "Lionel Messi";
  private final int ONE_HUNDRED_MILLISECONDS = 100;
  final String karting_accident = "karting accident";
  final String heart_attack = "heart attack";
  private final int DETAILS_TIME_INVALID = 6100;
  DeathNote deathnote = new DeathNoteimpl();
    /*1 Rule number 0 and negative rules do not exist in the DeathNote rules.
      * check that the exceptions are thrown correctly, that their type is the expected one, and that the message is not null, empty, or blank. */
    @Test
      void testRulesExceptions(){
      for (final int number : List.of(-1, 0, RULES.size() + 1)) {
        try {
          deathnote.getRule(number);
        } catch (IllegalArgumentException e) {
          System.out.println("ERRORE: " + e.getMessage());
        }
      }
    }

    /*2. No rule is empty or null in the DeathNote rules.
      * for all the valid rules, check that none is null or blank */
    @Test
    public void testNoRuleEmpty(){
        for (int i = 1 ; i <= RULES.size(); i++){
          assertNotNull(deathnote.getRule(i));
          assertFalse(deathnote.getRule(i).isBlank());
        }
    }
    

    /*3. The human whose name is written in the DeathNote will eventually die.
      * verify that the human has not been written in the notebook yet
      * write the human in the notebook
      * verify that the human has been written in the notebook
      * verify that another human has not been written in the notebook
      * verify that the empty string has not been written in the notebook */
    @Test
    public void testHumanDied(){
        assertFalse(deathnote.isNameWritten(Lamine_Yamal));
        deathnote.writeName(Lamine_Yamal);
        deathnote.isNameWritten(Lamine_Yamal);
        assertFalse(deathnote.isNameWritten(Lionel_Messi));
        assertFalse(deathnote.isNameWritten(""));
    }

    /*4. If the cause of death is written within the next 40 milliseconds of writing the person's name, it will happen.
     If the cause of death is not specified, the person will simply die of a heart attack.
      * check that writing a cause of death before writing a name throws the correct exception
      * write the name of a human in the notebook
      * verify that the cause of death is a heart attack
      * write the name of another human in the notebook
      * set the cause of death to "karting accident"
      * verify that the cause of death has been set correctly (returned true, and the cause is indeed "karting accident")
      * sleep for 100ms
      * try to change the cause of death 
      * verify that the cause of death has not been changed */
    @Test
    public void testCauseWritten() throws InterruptedException{
        try {
          deathnote.writeDeathCause(karting_accident);
        } catch (IllegalStateException e) {
          System.out.println("There's no name in the deathnote: " + e.getMessage());
        }
        deathnote.writeName(Lamine_Yamal);
        assertEquals(heart_attack, deathnote.getDeathCause(Lamine_Yamal));
        deathnote.writeName(Lionel_Messi);
        assertTrue(deathnote.writeDeathCause(karting_accident));
        assertEquals(karting_accident, deathnote.getDeathCause(Lionel_Messi));
        sleep(ONE_HUNDRED_MILLISECONDS);
        assertFalse(deathnote.writeDeathCause("jumped from skate"));
        assertEquals(karting_accident, deathnote.getDeathCause(Lionel_Messi));
    }

    /*5. After writing the cause of death, details of the death should be written in the next 6 seconds and 40 milliseconds of writing the death's cause.
      * check that writing the death details before writing a name throws the correct exception
      * write the name of a human in the notebook
      * verify that the details of the death are currently empty
      * set the details of the death to "ran for too long"
      * verify that death details have been set correctly (returned true, and the details are indeed "ran for too long")
      * write the name of another human in the notebook
      * sleep for 6100ms
      * try to change the details
      * verify that the details have not been changed */
    @Test
    public void testDetails() throws InterruptedException{
        try {
          deathnote.writeDetails(Lamine_Yamal);
        } catch (IllegalStateException e) {
          System.out.println("There is no name in the deathnote " + e.getMessage());
        }
        deathnote.writeName(Lamine_Yamal);
        assertEquals("",deathnote.getDeathDetails(Lamine_Yamal));
        assertTrue(deathnote.writeDetails("ran for too long"));
        assertEquals("ran for too long", deathnote.getDeathDetails(Lamine_Yamal));
        deathnote.writeName(Lionel_Messi);
        sleep(DETAILS_TIME_INVALID);
        assertFalse(deathnote.writeDetails("fell while skating"));
        assertEquals("", deathnote.getDeathDetails(Lionel_Messi));
    }
}