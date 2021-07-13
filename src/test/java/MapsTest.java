import io.github.artkonr.ensure.Ensure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MapsTest {

  @Test
  void notEmpty_nullArgument_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.notEmpty((Map<?, ?>) null));
  }

  @Test
  void notEmpty_emptyArgument_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.notEmpty(new HashMap<>()));
  }

  @Test
  void notEmpty_notEmptyArgument_doesNotThrow() {
    Assertions.assertDoesNotThrow(() -> Ensure.notEmpty(Map.of(1, 2, 3, 4)));
  }

  @Test
  void notEmpty_emptyArgument_withArgumentName_nameIsPresentInMessage() {
    try {
      Ensure.notEmpty(new HashMap<>(), "arg");
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
      Ensure.notEmpty(new HashMap<>(), null);
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
      Ensure.notEmpty(new HashMap<>(), "");
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
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.deepNotEmpty(new HashSet<>()));
  }

  @Test
  void deepNotEmpty_nullInKeys_throws() {
    Map<Object, Object> l = new HashMap<>(3);
    l.put(new Object(), new Object());
    l.put(new Object(), new Object());
    l.put(null, new Object());
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.deepNotEmpty(l));
  }

  @Test
  void deepNotEmpty_nullInValues_doesNotThrow() {
    Map<Object, Object> l = new HashMap<>(3);
    l.put(new Object(), new Object());
    l.put(new Object(), new Object());
    l.put(new Object(), null);
    Assertions.assertDoesNotThrow(() -> Ensure.deepNotEmpty(l));
  }

  @Test
  void deepNotEmpty_notEmptyArgument_doesNotThrow() {
    Assertions.assertDoesNotThrow(() -> Ensure.deepNotEmpty(Map.of(new Object(), new Object())));
  }

  @Test
  void deepNotEmpty_emptyArgument_withArgumentName_nameIsPresentInMessage() {
    try {
      Ensure.deepNotEmpty(new HashMap<>(), "arg");
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
      Ensure.deepNotEmpty(new HashMap<>(), null);
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
      Ensure.deepNotEmpty(new HashMap<>(), "");
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
    Map<Object, Object> l = new HashMap<>(3);
    l.put(new Object(), new Object());
    l.put(new Object(), new Object());
    l.put(null, new Object());
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
    Map<Object, Object> l = new HashMap<>(3);
    l.put(new Object(), new Object());
    l.put(new Object(), new Object());
    l.put(null, new Object());
    try {
      Ensure.notEmpty(l, null);
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
    Map<Object, Object> l = new HashMap<>(3);
    l.put(new Object(), new Object());
    l.put(new Object(), new Object());
    l.put(null, new Object());
    try {
      Ensure.notEmpty(l, "");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=all-non-null"));
      Assertions.assertFalse(message.contains("elementOf"));
    }
  }


  
}
