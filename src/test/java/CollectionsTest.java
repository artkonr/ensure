import io.github.artkonr.ensure.Ensure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionsTest {

  @Test
  void notEmpty_nullArgument_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.notEmpty((Collection<?>) null));
  }

  @Test
  void notEmpty_emptyArgument_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.notEmpty(new HashSet<>()));
  }

  @Test
  void notEmpty_notEmptyArgument_doesNotThrow() {
    Assertions.assertDoesNotThrow(() -> Ensure.notEmpty(Set.of(1, 2, 3)));
  }

  @Test
  void notEmpty_emptyArgument_withArgumentName_nameIsPresentInMessage() {
    try {
      Ensure.notEmpty(new HashSet<>(), "arg");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null&non-empty"));
      Assertions.assertTrue(message.contains("argName=arg"));
    }
  }

  @Test
  void notEmpty_emptyArgument_nullArgumentName_nameNotPresentInMessage() {
    try {
      Ensure.notEmpty(new HashSet<>(), null);
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null&non-empty"));
      Assertions.assertFalse(message.contains("argName"));
    }
  }

  @Test
  void notEmpty_emptyArgument_blankArgumentName_nameNotPresentInMessage() {
    try {
      Ensure.notEmpty(new HashSet<>(), "");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null&non-empty"));
      Assertions.assertFalse(message.contains("argName"));
    }
  }

  @Test
  void deepNotEmpty_nullArgument_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.deepNotEmpty((Collection<?>) null));
  }

  @Test
  void deepNotEmpty_emptyArgument_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.deepNotEmpty(new ArrayList<>()));
  }

  @Test
  void deepNotEmpty_notEmptyArgument_doesNotThrow() {
    Assertions.assertDoesNotThrow(() -> Ensure.deepNotEmpty(List.of(new Object(), new Object())));
  }

  @Test
  void deepNotEmpty_emptyArgument_withArgumentName_nameIsPresentInMessage() {
    try {
      Ensure.deepNotEmpty(new HashSet<>(), "arg");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null&non-empty"));
      Assertions.assertTrue(message.contains("argName=arg"));
    }
  }

  @Test
  void deepNotEmpty_emptyArgument_nullArgumentName_nameNotPresentInMessage() {
    try {
      Ensure.deepNotEmpty(new HashSet<>(), null);
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null&non-empty"));
      Assertions.assertFalse(message.contains("argName"));
    }
  }

  @Test
  void deepNotEmpty_emptyArgument_blankArgumentName_nameNotPresentInMessage() {
    try {
      Ensure.deepNotEmpty(new HashSet<>(), "");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null&non-empty"));
      Assertions.assertFalse(message.contains("argName"));
    }
  }

  @Test
  void deepNotEmpty_notEmptyArgument_nullInCollection_withArgumentName_nameIsPresentInMessage() {
    List<Object> l = new ArrayList<>(3);
    l.add(new Object());
    l.add(new Object());
    l.add(null);
    try {
      Ensure.deepNotEmpty(l, "arg");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=all-non-null"));
      Assertions.assertTrue(message.contains("elementOf=arg"));
    }
  }

  @Test
  void deepNotEmpty_notEmptyArgument_nullInCollection_nullArgumentName_nameNotPresentInMessage() {
    List<Object> l = new ArrayList<>(3);
    l.add(new Object());
    l.add(new Object());
    l.add(null);
    try {
      Ensure.deepNotEmpty(l, null);
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=all-non-null"));
      Assertions.assertFalse(message.contains("elementOf"));
    }
  }

  @Test
  void deepNotEmpty_emptyArgument_nullInCollection_blankArgumentName_nameNotPresentInMessage() {
    List<Object> l = new ArrayList<>(3);
    l.add(new Object());
    l.add(new Object());
    l.add(null);
    try {
      Ensure.deepNotEmpty(l, "");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=all-non-null"));
      Assertions.assertFalse(message.contains("elementOf"));
    }
  }


  
}
