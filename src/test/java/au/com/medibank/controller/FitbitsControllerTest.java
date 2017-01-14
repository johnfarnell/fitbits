package au.com.medibank.controller;

import au.com.medibank.dao.FitbitsEntitiesDAO;
import au.com.medibank.entity.Pitch;
import au.com.medibank.entity.Trainee;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class FitbitsControllerTest {
    @Test
    public void testExecuteInstructions() {
        FitbitsController target = new FitbitsController();
        /*
        Mock the entities involved
         */
        FitbitsEntitiesDAO mockFitbitsEntitiesDAO = mock(FitbitsEntitiesDAO.class);
        Pitch pitch = mock(Pitch.class);
        Trainee trainee1 = mock(Trainee.class);
        Trainee trainee2 = mock(Trainee.class);

        //Mock Trainee reads
        when(mockFitbitsEntitiesDAO.getNextTrainee()).thenReturn(trainee1, trainee2, null);

        //Mock Pitch reads
        when(mockFitbitsEntitiesDAO.getPitch()).thenReturn(pitch);

        target.executeInstructions(mockFitbitsEntitiesDAO);

        //Each trainee should have had their instructions executed exactly once

        verify(trainee1, times(1)).executeInstructions(pitch);
        verify(trainee2, times(1)).executeInstructions(pitch);
    }

}
