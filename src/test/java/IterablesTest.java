import io.github.artkonr.ensure.Ensure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class IterablesTest {

  private static class TestIterable<T> implements Iterable<T> {

    private final List<T> items;

    private TestIterable(List<T> items) {
      this.items = items;
    }

    private TestIterable() {
      this(Collections.emptyList());
    }

    @Override
    public Iterator<T> iterator() {
      return items.iterator();
    }

  }

  @Test
  void notEmpty_nullArgument_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.notEmpty((Iterable<?>) null));
  }

  @Test
  void notEmpty_emptyArgument_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.notEmpty(new TestIterable<>()));
  }

  @Test
  void notEmpty_notEmptyArgument_doesNotThrow() {
    Assertions.assertDoesNotThrow(() -> Ensure.notEmpty(new TestIterable<>(List.of(1, 2, 3))));
  }

  @Test
  void notEmpty_emptyArgument_withArgumentName_nameIsPresentInMessage() {
    try {
      Ensure.notEmpty(new TestIterable<>(), "arg");
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
      Ensure.notEmpty(new TestIterable<>(), null);
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
      Ensure.notEmpty(new TestIterable<>(), "");
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
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.deepNotEmpty((Iterable<?>) null));
  }

  @Test
  void deepNotEmpty_emptyArgument_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.deepNotEmpty(new TestIterable<>()));
  }

  @Test
  void deepNotEmpty_notEmptyArgument_doesNotThrow() {
    Assertions.assertDoesNotThrow(() -> Ensure.deepNotEmpty(new TestIterable<>(List.of(new Object(), new Object()))));
  }

  @Test
  void deepNotEmpty_emptyArgument_withArgumentName_nameIsPresentInMessage() {
    try {
      Ensure.deepNotEmpty(new TestIterable<>(), "arg");
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
      Ensure.deepNotEmpty(new TestIterable<>(), null);
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
      Ensure.deepNotEmpty(new TestIterable<>(), "");
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
      Ensure.deepNotEmpty(new TestIterable<>(l), "arg");
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
      Ensure.deepNotEmpty(new TestIterable<>(l), null);
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
      Ensure.deepNotEmpty(new TestIterable<>(l), "");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=all-non-null"));
      Assertions.assertFalse(message.contains("elementOf"));
    }
  }


  
}
