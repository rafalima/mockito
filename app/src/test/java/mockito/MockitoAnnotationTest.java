package mockito;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MockitoAnnotationTest {

    @Mock
    List<String> mockedList;

    @Spy
    List<String> spiedList = new ArrayList<String>();

    @Mock
    List captorMockedList;

    @Captor
    ArgumentCaptor argCaptor;

    @Test
    public void whenUseCaptorAnnotation_thenTheSam() {
        captorMockedList.add("one");
        Mockito.verify(captorMockedList).add(argCaptor.capture());

        assertEquals("one", argCaptor.getValue());
    }

    @Test
    public void whenUseSpyAnnotation_thenSpyIsInjectedCorrectly() {
        spiedList.add("one");
        spiedList.add("two");

        Mockito.verify(spiedList).add("one");
        Mockito.verify(spiedList).add("two");

        assertEquals(2, spiedList.size());

        Mockito.when(spiedList.size()).thenReturn(100);
        assertEquals(100, spiedList.size());
    }

    @Test
    public void whenNotUseSpyAnnotation_thenCorrect() {
        List<String> spyList = Mockito.spy(new ArrayList<String>());

        spyList.add("one");
        spyList.add("two");

        Mockito.verify(spyList).add("one");
        Mockito.verify(spyList).add("two");

        assertEquals(2, spyList.size());

        Mockito.doReturn(100).when(spyList).size();
        assertEquals(100, spyList.size());
    }

    @Test
    public void whenUseMockAnnotation_thenMockIsInjected() {
        mockedList.add("one");
        Mockito.verify(mockedList).add("one");
        assertEquals(0,mockedList.size());

        Mockito.when(mockedList.size()).thenReturn(10);
        assertEquals(10, mockedList.size());
    }

    @Test
    public void whenNotUseMockAnnotation_thenCorrect() {
        List mockList = Mockito.mock(ArrayList.class);

        mockList.add("one");
        Mockito.verify(mockList).add("one");
        assertEquals(0, mockList.size());

        Mockito.when(mockList.size()).thenReturn(100);
        assertEquals(100, mockList.size());
    }

}
