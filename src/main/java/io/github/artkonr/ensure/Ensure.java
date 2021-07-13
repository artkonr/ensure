package io.github.artkonr.ensure;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

/**
 * A collection of argument checks. Non-instantiable.
 * <p>Provides trivial validation mechanisms to check for:
 * <ul>
 *   <li>object non-nullity</li>
 *   <li>{@link String} non-emptiness</li>
 *   <li>{@link Collection}, {@link Iterable} or {@link Map} non-emptiness</li>
 *   <li>other assorted checks</li>
 * </ul>
 * @author @artkonr
 */
public final class Ensure {

  // general

  /**
   * Checks if the provided object is
   *  {@code null} and throws if it is.
   * @param val checked object
   * @throws IllegalArgumentException if check fails
   */
  public static void notNull(Object val) {
    if (val == null)
      throw new IllegalArgumentException(MESSAGE_START
              + EXPECT_DECL + NON_NULL + SEP + VAL_DECL + null);
  }

  /**
   * Checks if the provided object is
   *  {@code null} and throws if it is.
   * @param val checked object
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void notNull(Object val, String argName) {
    if (argNameValid(argName)) {
      if (val == null)
        throw new IllegalArgumentException(MESSAGE_START + NAME_DECL + argName
                + SEP + EXPECT_DECL + NON_NULL + SEP + VAL_DECL + null);
    } else {
      notNull(val);
    }
  }

  /**
   * Applies {@link Ensure#notNull(Object)} to
   *  all of the provided objects.
   * @param val first checked object
   * @param other other checked objects as varargs
   * @throws IllegalArgumentException if check fails
   */
  public static void neitherNull(Object val, Object... other) {
    notNull(val);
    if (other != null)
      for (Object o : other)
        notNull(o);
  }

  // strings

  /**
   * Checks if the provided {@link String} is
   *  {@code null} or has {@code 0} length (checked
   *  by {@link String#isEmpty()}), or contains only
   *  whitespace chars (checked by {@link String#isBlank()})
   *  and throws if it is.
   * @param val checked object
   * @throws IllegalArgumentException if check fails
   */
  public static void notBlank(String val) {
    if (val == null || val.isBlank())
      throw new IllegalArgumentException(fmtString(val,
              NON_NULL + '&' + NOT_BLANK));
  }

  /**
   * Checks if the provided {@link String} is
   *  {@code null} or has {@code 0} length (checked
   *  by {@link String#isEmpty()}), or contains only
   *  whitespace chars (checked by {@link String#isBlank()})
   *  and throws if it is.
   * @param val checked object
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void notBlank(String val, String argName) {
    if (argNameValid(argName)) {
      if (val == null || val.isBlank())
        throw new IllegalArgumentException(fmtString(argName, val,
                NON_NULL + '&' + NOT_BLANK));
    } else {
      notBlank(val);
    }
  }

  /**
   * Checks if the provided {@link String} has
   *  {@code 0} length (checked by {@link String#isEmpty()}),
   *  or contains only whitespace chars (checked by {@link
   *  String#isBlank()}) and throws if it is.
   * <p>{@code null}-{@link String} is considered valid.
   * @param val checked object
   * @throws IllegalArgumentException if check fails
   */
  public static void nullableNotBlank(String val) {
    if (val == null)
      return;

    if (val.isBlank())
      throw new IllegalArgumentException(fmtString(val, NOT_BLANK));
  }

  /**
   * Checks if the provided {@link String} has
   *  {@code 0} length (checked by {@link String#isEmpty()}),
   *  or contains only whitespace chars (checked by {@link
   *  String#isBlank()}) and throws if it is.
   * <p>{@code null}-{@link String} is considered valid.
   * @param val checked object
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void nullableNotBlank(String val, String argName) {
    if (val == null)
      return;

    if (argNameValid(argName)) {
      if (val.isBlank())
        throw new IllegalArgumentException(fmtString(argName, val, NOT_BLANK));
    } else {
      nullableNotBlank(val);
    }
  }

  /**
   * Applies {@link Ensure#notBlank(String)} to
   *  all of the provided objects.
   * @param val first checked object
   * @param other other checked objects as varargs
   * @throws IllegalArgumentException if check fails
   */
  public static void neitherBlank(String val, String... other) {
    notBlank(val);
    if (other != null)
      for (String s : other)
        notBlank(s);
  }

  /**
   * Applies {@link Ensure#nullableNotBlank(String)} to
   *  all of the provided objects.
   * @param val first checked object
   * @param other other checked objects as varargs
   * @throws IllegalArgumentException if check fails
   */
  public static void neitherNullableNotBlank(String val, String... other) {
    nullableNotBlank(val);
    if (other != null)
      for (String s : other)
        nullableNotBlank(s);
  }

  // numerics

  /**
   * Checks if the provided {@code int} is
   *  <b>positive</b> and throws if it is not.
   * @param val value to check
   * @throws IllegalArgumentException if check fails
   */
  public static void isPositive(int val) {
    if (val < 1)
      throw new IllegalArgumentException(fmtInt(val, POSITIVE));
  }

  /**
   * Checks if the provided {@code int} is
   *  <b>positive</b> and throws if it is not.
   * @param val value to check
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void isPositive(int val, String argName) {
    if (argNameValid(argName)) {
      if (val < 1)
        throw new IllegalArgumentException(fmtInt(argName, val, POSITIVE));
    } else {
      isPositive(val);
    }
  }

  /**
   * Checks if the provided {@code int} is
   *  <b>non-negative</b> and throws if it is not.
   * @param val value to check
   * @throws IllegalArgumentException if check fails
   */
  public static void isNonNegative(int val) {
    if (val < 0)
      throw new IllegalArgumentException(fmtInt(val, NON_NEG));
  }

  /**
   * Checks if the provided {@code int} is
   *  <b>non-negative</b> and throws if it is not.
   * @param val value to check
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void isNonNegative(int val, String argName) {
    if (argNameValid(argName)) {
      if (val < 0)
        throw new IllegalArgumentException(fmtInt(argName, val, NON_NEG));
    } else {
      isNonNegative(val);
    }
  }

  /**
   * Applies {@link Ensure#isPositive(int)} to
   *  all of the provided objects.
   * @param val first checked object
   * @param other other checked objects as varargs
   * @throws IllegalArgumentException if check fails
   */
  public static void arePositive(int val, int... other) {
    isPositive(val);
    if (other != null)
      for (int i : other)
        isPositive(i);
  }

  /**
   * Applies {@link Ensure#isNonNegative(int)} to
   *  all of the provided objects.
   * @param val first checked object
   * @param other other checked objects as varargs
   * @throws IllegalArgumentException if check fails
   */
  public static void areNonNegative(int val, int... other) {
    isNonNegative(val);
    if (other != null)
      for (int i : other)
        isNonNegative(i);
  }


  /**
   * Checks if the provided {@code long} is
   *  <b>positive</b> and throws if it is not.
   * @param val value to check
   * @throws IllegalArgumentException if check fails
   */
  public static void isPositive(long val) {
    if (val < 1)
      throw new IllegalArgumentException(fmtLong(val, POSITIVE));
  }

  /**
   * Checks if the provided {@code long} is
   *  <b>positive</b> and throws if it is not.
   * @param val value to check
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void isPositive(long val, String argName) {
    if (argNameValid(argName)) {
      if (val < 1)
        throw new IllegalArgumentException(fmtLong(argName, val, POSITIVE));
    } else {
      isPositive(val);
    }
  }

  /**
   * Checks if the provided {@code long} is
   *  <b>non-negative</b> and throws if it is not.
   * @param val value to check
   * @throws IllegalArgumentException if check fails
   */
  public static void isNonNegative(long val) {
    if (val < 0)
      throw new IllegalArgumentException(fmtLong(val, NON_NEG));
  }

  /**
   * Checks if the provided {@code long} is
   *  <b>non-negative</b> and throws if it is not.
   * @param val value to check
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void isNonNegative(long val, String argName) {
    if (argNameValid(argName)) {
      if (val < 0)
        throw new IllegalArgumentException(fmtLong(argName, val, NON_NEG));
    } else {
      isNonNegative(val);
    }
  }

  /**
   * Applies {@link Ensure#isPositive(long)} to
   *  all of the provided objects.
   * @param val first checked object
   * @param other other checked objects as varargs
   * @throws IllegalArgumentException if check fails
   */
  public static void arePositive(long val, long... other) {
    isPositive(val);
    if (other != null)
      for (long i : other)
        isPositive(i);
  }

  /**
   * Applies {@link Ensure#isNonNegative(long)} to
   *  all of the provided objects.
   * @param val first checked object
   * @param other other checked objects as varargs
   * @throws IllegalArgumentException if check fails
   */
  public static void areNonNegative(long val, long... other) {
    isNonNegative(val);
    if (other != null)
      for (long i : other)
        isNonNegative(i);
  }
  

  /**
   * Checks if the provided {@code double} is
   *  <b>positive</b> and throws if it is not.
   * @param val value to check
   * @throws IllegalArgumentException if check fails
   */
  public static void isPositive(double val) {
    if (val < 1)
      throw new IllegalArgumentException(fmtDouble(val, POSITIVE));
  }

  /**
   * Checks if the provided {@code double} is
   *  <b>positive</b> and throws if it is not.
   * @param val value to check
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void isPositive(double val, String argName) {
    if (argNameValid(argName)) {
      if (val < 1)
        throw new IllegalArgumentException(fmtDouble(argName, val, POSITIVE));
    } else {
      isPositive(val);
    }
  }

  /**
   * Checks if the provided {@code double} is
   *  <b>non-negative</b> and throws if it is not.
   * @param val value to check
   * @throws IllegalArgumentException if check fails
   */
  public static void isNonNegative(double val) {
    if (val < 0)
      throw new IllegalArgumentException(fmtDouble(val, NON_NEG));
  }

  /**
   * Checks if the provided {@code double} is
   *  <b>non-negative</b> and throws if it is not.
   * @param val value to check
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void isNonNegative(double val, String argName) {
    if (argNameValid(argName)) {
      if (val < 0)
        throw new IllegalArgumentException(fmtDouble(argName, val, NON_NEG));
    } else {
      isNonNegative(val);
    }
  }

  /**
   * Applies {@link Ensure#isPositive(double)} to
   *  all of the provided objects.
   * @param val first checked object
   * @param other other checked objects as varargs
   * @throws IllegalArgumentException if check fails
   */
  public static void arePositive(double val, double... other) {
    isPositive(val);
    if (other != null)
      for (double i : other)
        isPositive(i);
  }

  /**
   * Applies {@link Ensure#isNonNegative(double)} to
   *  all of the provided objects.
   * @param val first checked object
   * @param other other checked objects as varargs
   * @throws IllegalArgumentException if check fails
   */
  public static void areNonNegative(double val, double... other) {
    isNonNegative(val);
    if (other != null)
      for (double i : other)
        isNonNegative(i);
  }


  /**
   * Checks if the provided {@code float} is
   *  <b>positive</b> and throws if it is not.
   * @param val value to check
   * @throws IllegalArgumentException if check fails
   */
  public static void isPositive(float val) {
    if (val < 1)
      throw new IllegalArgumentException(fmtFloat(val, POSITIVE));
  }

  /**
   * Checks if the provided {@code float} is
   *  <b>positive</b> and throws if it is not.
   * @param val value to check
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void isPositive(float val, String argName) {
    if (argNameValid(argName)) {
      if (val < 1)
        throw new IllegalArgumentException(fmtFloat(argName, val, POSITIVE));
    } else {
      isPositive(val);
    }
  }

  /**
   * Checks if the provided {@code float} is
   *  <b>non-negative</b> and throws if it is not.
   * @param val value to check
   * @throws IllegalArgumentException if check fails
   */
  public static void isNonNegative(float val) {
    if (val < 0)
      throw new IllegalArgumentException(fmtFloat(val, NON_NEG));
  }

  /**
   * Checks if the provided {@code float} is
   *  <b>non-negative</b> and throws if it is not.
   * @param val value to check
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void isNonNegative(float val, String argName) {
    if (argNameValid(argName)) {
      if (val < 0)
        throw new IllegalArgumentException(fmtFloat(argName, val, NON_NEG));
    } else {
      isNonNegative(val);
    }
  }

  /**
   * Applies {@link Ensure#isPositive(float)} to
   *  all of the provided objects.
   * @param val first checked object
   * @param other other checked objects as varargs
   * @throws IllegalArgumentException if check fails
   */
  public static void arePositive(float val, float... other) {
    isPositive(val);
    if (other != null)
      for (float i : other)
        isPositive(i);
  }

  /**
   * Applies {@link Ensure#isNonNegative(float)} to
   *  all of the provided objects.
   * @param val first checked object
   * @param other other checked objects as varargs
   * @throws IllegalArgumentException if check fails
   */
  public static void areNonNegative(float val, float... other) {
    isNonNegative(val);
    if (other != null)
      for (float i : other)
        isNonNegative(i);
  }
  

  /**
   * Checks if the provided {@code short} is
   *  <b>positive</b> and throws if it is not.
   * @param val value to check
   * @throws IllegalArgumentException if check fails
   */
  public static void isPositive(short val) {
    if (val < 1)
      throw new IllegalArgumentException(fmtShort(val, POSITIVE));
  }

  /**
   * Checks if the provided {@code short} is
   *  <b>positive</b> and throws if it is not.
   * @param val value to check
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void isPositive(short val, String argName) {
    if (argNameValid(argName)) {
      if (val < 1)
        throw new IllegalArgumentException(fmtShort(argName, val, POSITIVE));
    } else {
      isPositive(val);
    }
  }

  /**
   * Checks if the provided {@code short} is
   *  <b>non-negative</b> and throws if it is not.
   * @param val value to check
   * @throws IllegalArgumentException if check fails
   */
  public static void isNonNegative(short val) {
    if (val < 0)
      throw new IllegalArgumentException(fmtShort(val, NON_NEG));
  }

  /**
   * Checks if the provided {@code short} is
   *  <b>non-negative</b> and throws if it is not.
   * @param val value to check
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void isNonNegative(short val, String argName) {
    if (argNameValid(argName)) {
      if (val < 0)
        throw new IllegalArgumentException(fmtShort(argName, val, NON_NEG));
    } else {
      isNonNegative(val);
    }
  }

  /**
   * Applies {@link Ensure#isPositive(short)} to
   *  all of the provided objects.
   * @param val first checked object
   * @param other other checked objects as varargs
   * @throws IllegalArgumentException if check fails
   */
  public static void arePositive(short val, short... other) {
    isPositive(val);
    if (other != null)
      for (short i : other)
        isPositive(i);
  }

  /**
   * Applies {@link Ensure#isNonNegative(short)} to
   *  all of the provided objects.
   * @param val first checked object
   * @param other other checked objects as varargs
   * @throws IllegalArgumentException if check fails
   */
  public static void areNonNegative(short val, short... other) {
    isNonNegative(val);
    if (other != null)
      for (short i : other)
        isNonNegative(i);
  }


  // collections

  /**
   * Checks if the provided {@link Collection} is neither
   *  {@code null} nor empty (checked by {@link Collection#isEmpty()})
   *  and throws if it is.
   * @param val checked object
   * @throws IllegalArgumentException if check fails
   */
  public static void notEmpty(Collection<?> val) {
    if (val == null || val.isEmpty())
      throw new IllegalArgumentException(MESSAGE_START + EXPECT_DECL +
              NON_NULL + '&' + NOT_EMPTY + SEP + VAL_DECL + "false");
  }

  /**
   * Checks if the provided {@link Collection} is neither
   *  {@code null} nor empty (checked by {@link Collection#isEmpty()})
   *  and throws if it is.
   * @param val checked object
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void notEmpty(Collection<?> val, String argName) {
    if (argNameValid(argName)) {
      if (val == null || val.isEmpty())
        throw new IllegalArgumentException(MESSAGE_START + NAME_DECL + argName +
                SEP + EXPECT_DECL + NON_NULL + '&' + NOT_EMPTY + SEP + VAL_DECL + "false");
    } else {
      notEmpty(val);
    }
  }

  /**
   * Checks if the provided {@link Collection} is neither
   *  {@code null} nor empty (checked by {@link Collection#isEmpty()}),
   *  nor any of its element is {@code null} and throws if it is.
   * @param val checked object
   * @throws IllegalArgumentException if check fails
   */
  public static void deepNotEmpty(Collection<?> val) {
    notEmpty(val);
    for (Object o : val)
      if (o == null)
        throw new IllegalArgumentException(MESSAGE_START + EXPECT_DECL + ALL_NON_NULL
                + SEP + VAL_DECL + "false");
  }

  /**
   * Checks if the provided {@link Collection} is neither
   *  {@code null} nor empty (checked by {@link Collection#isEmpty()}),
   *  nor any of its element is {@code null} and throws if it is.
   * @param val checked object
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void deepNotEmpty(Collection<?> val, String argName) {
    if (argNameValid(argName)) {
      notEmpty(val, argName);
      for (Object o : val)
        if (o == null) {
          throw new IllegalArgumentException(MESSAGE_START
                  + "elementOf=" + argName
                  + SEP + EXPECT_DECL + ALL_NON_NULL
                  + SEP + VAL_DECL + "false");
        }
    } else {
      deepNotEmpty(val);
    }
  }


  /**
   * Checks if the provided {@link Iterable} is neither
   *  {@code null} nor empty and throws if it is.
   * @param val checked object
   * @throws IllegalArgumentException if check fails
   */
  public static void notEmpty(Iterable<?> val) {
    if (val == null || !val.iterator().hasNext())
      throw new IllegalArgumentException(MESSAGE_START + EXPECT_DECL +
              NON_NULL + '&' + NOT_EMPTY + SEP + VAL_DECL + "false");
  }

  /**
   * Checks if the provided {@link Iterable} is neither
   *  {@code null} nor empty and throws if it is.
   * @param val checked object
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void notEmpty(Iterable<?> val, String argName) {
    if (argNameValid(argName)) {
      if (val == null || !val.iterator().hasNext())
        throw new IllegalArgumentException(MESSAGE_START + NAME_DECL + argName +
                SEP + EXPECT_DECL + NON_NULL + '&' + NOT_EMPTY + SEP + VAL_DECL + "false");
    } else {
      notEmpty(val);
    }
  }

  /**
   * Checks if the provided {@link Iterable} is neither
   *  {@code null} nor empty, nor any of its element is
   *  {@code null} and throws if it is.
   * @param val checked object
   * @throws IllegalArgumentException if check fails
   */
  public static void deepNotEmpty(Iterable<?> val) {
    notEmpty(val);
    for (Object o : val)
      if (o == null)
        throw new IllegalArgumentException(MESSAGE_START + EXPECT_DECL + ALL_NON_NULL
                + SEP + VAL_DECL + "false");
  }

  /**
   * Checks if the provided {@link Iterable} is neither
   *  {@code null} nor empty, nor any of its element is
   *  {@code null} and throws if it is.
   * @param val checked object
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void deepNotEmpty(Iterable<?> val, String argName) {
    if (argNameValid(argName)) {
      notEmpty(val, argName);
      for (Object o : val)
        if (o == null) {
          throw new IllegalArgumentException(MESSAGE_START
                  + "elementOf=" + argName
                  + SEP + EXPECT_DECL + ALL_NON_NULL
                  + SEP + VAL_DECL + "false");
        }
    } else {
      deepNotEmpty(val);
    }
  }


  /**
   * Checks if the provided {@link Map} is neither
   *  {@code null} nor empty (checked by {@link Map#isEmpty()})
   *  and throws if it is.
   * @param val checked object
   * @throws IllegalArgumentException if check fails
   */
  public static void notEmpty(Map<?, ?> val) {
    if (val == null || val.isEmpty())
      throw new IllegalArgumentException(MESSAGE_START + EXPECT_DECL +
              NON_NULL + '&' + NOT_EMPTY + SEP + VAL_DECL + "false");
  }

  /**
   * Checks if the provided {@link Map} is neither
   *  {@code null} nor empty (checked by {@link Map#isEmpty()})
   *  and throws if it is.
   * @param val checked object
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void notEmpty(Map<?, ?> val, String argName) {
    if (argNameValid(argName)) {
      if (val == null || val.isEmpty())
        throw new IllegalArgumentException(MESSAGE_START + NAME_DECL + argName +
                SEP + EXPECT_DECL + NON_NULL + '&' + NOT_EMPTY + SEP + VAL_DECL + "false");
    } else {
      notEmpty(val);
    }
  }

  /**
   * Checks if the provided {@link Map} is neither
   *  {@code null} nor empty (checked by {@link Map#isEmpty()}),
   *  nor any of its <b>keys</b> is {@code null} and throws if it is.
   * @param val checked object
   * @throws IllegalArgumentException if check fails
   */
  public static void deepNotEmpty(Map<?, ?> val) {
    notEmpty(val);
    for (Map.Entry<?, ?> entry : val.entrySet())
      if (entry.getKey() == null)
        throw new IllegalArgumentException(MESSAGE_START + EXPECT_DECL + ALL_NON_NULL
                + SEP + VAL_DECL + "false");
  }

  /**
   * Checks if the provided {@link Map} is neither
   *  {@code null} nor empty (checked by {@link Map#isEmpty()}),
   *  nor any of its <b>keys</b> is {@code null} and throws if it is.
   * @param val checked object
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void deepNotEmpty(Map<?, ?> val, String argName) {
    if (argNameValid(argName)) {
      notEmpty(val, argName);
      for (Map.Entry<?, ?> entry : val.entrySet())
        if (entry.getKey() == null) {
          throw new IllegalArgumentException(MESSAGE_START
                  + "elementOf=" + argName
                  + SEP + EXPECT_DECL + ALL_NON_NULL
                  + SEP + VAL_DECL + "false");
        }
    } else {
      deepNotEmpty(val);
    }
  }


  // other

  /**
   * Checks if the provided {@link String} is {@link
   *  Ensure#notBlank(String)} and is a valid URI (
   *  checked by {@link URI#create(String)})
   *  and throws if it is not.
   * @param val checked object
   * @return created {@link URI}
   * @throws IllegalArgumentException if check fails
   */
  public static URI isValidUrl(String val) {
    notBlank(val);
    try {
      return URI.create(val);
    } catch (IllegalArgumentException x) {
      throw new IllegalArgumentException(fmtString(val,
              NON_NULL + '&' + NOT_BLANK + '&' + "is-url") + ": " + x.getMessage());
    }
  }

  /**
   * Checks if the provided {@link String} is {@link
   *  Ensure#notBlank(String)} and is a valid URI (
   *  checked by {@link URI#create(String)})
   *  and throws if it is not.
   * @param val checked object
   * @param argName name of the argument
   * @return created {@link URI}
   * @throws IllegalArgumentException if check fails
   */
  public static URI isValidUrl(String val, String argName) {
    if (argNameValid(argName)) {
      notBlank(val, argName);
      try {
        return URI.create(val);
      } catch (IllegalArgumentException x) {
        throw new IllegalArgumentException(fmtString(argName, val,
                NON_NULL + '&' + NOT_BLANK + '&' + "is-url") + ": " + x.getMessage());
      }
    } else {
      return isValidUrl(val);
    }
  }

  /**
   * Checks if a provided {@code int} may
   *  represent a valid HTTP status, i.e.
   *  belongs to an interval of {@code [100;600)}.
   * @param val value to check
   * @throws IllegalArgumentException if check fails
   */
  public static void isHttpStatus(int val) {
    if (val < 100 || val > 599)
      throw new IllegalArgumentException(fmtInt(val, "'in [100;600)'"));
  }

  /**
   * Checks if a provided {@code int} may
   *  represent a valid HTTP status, i.e.
   *  belongs to an interval of {@code [100;600)}.
   * @param val value to check
   * @param argName name of the argument
   * @throws IllegalArgumentException if check fails
   */
  public static void isHttpStatus(int val, String argName) {
    if (argNameValid(argName)) {
      if (val < 100 || val > 599)
        throw new IllegalArgumentException(fmtInt(argName, val, "'in [100;600)'"));
    } else {
      isHttpStatus(val);
    }
  }

  /**
   * Applies {@link Ensure#isHttpStatus(int)} to
   *  every provided object.
   * @param val first checked object
   * @param other other checked objects as varargs
   * @throws IllegalArgumentException if check fails
   */
  public static void areHttpStatuses(int val, int... other) {
    isHttpStatus(val);
    if (other != null)
      for (int st : other)
        isHttpStatus(st);
  }


  private static final String MESSAGE_START = "Bad arg: ";
  private static final String NAME_DECL = "argName=";
  private static final String TYPE_DECL = "type=";
  private static final String VAL_DECL = "actual=";
  private static final String EXPECT_DECL = "expected=";
  private static final char SEP = ';';

  private static final String POSITIVE = ">0";
  private static final String NON_NEG = ">=0";
  private static final String NON_NULL = "non-null";
  private static final String NOT_BLANK = "non-blank";
  private static final String NOT_EMPTY = "non-empty";
  private static final String ALL_NON_NULL = "all-non-null";

  private static boolean argNameValid(String argName) {
    return argName != null && !argName.isBlank();
  }

  private static String fmtString(String argName, String val, String expectation) {
    return MESSAGE_START + NAME_DECL + argName
            + SEP + TYPE_DECL + String.class.getSimpleName()
            + SEP + EXPECT_DECL + expectation
            + SEP + VAL_DECL + val;
  }

  private static String fmtInt(String argName, int val, String expectation) {
    return MESSAGE_START + NAME_DECL + argName
            + SEP + TYPE_DECL + int.class.getSimpleName()
            + SEP + EXPECT_DECL + expectation
            + SEP + VAL_DECL + val;
  }

  private static String fmtLong(String argName, long val, String expectation) {
    return MESSAGE_START + NAME_DECL + argName
            + SEP + TYPE_DECL + long.class.getSimpleName()
            + SEP + EXPECT_DECL + expectation
            + SEP + VAL_DECL + val;
  }

  private static String fmtShort(String argName, short val, String expectation) {
    return MESSAGE_START + NAME_DECL + argName
            + SEP + TYPE_DECL + short.class.getSimpleName()
            + SEP + EXPECT_DECL + expectation
            + SEP + VAL_DECL + val;
  }

  private static String fmtDouble(String argName, double val, String expectation) {
    return MESSAGE_START + NAME_DECL + argName
            + SEP + TYPE_DECL + double.class.getSimpleName()
            + SEP + EXPECT_DECL + expectation
            + SEP + VAL_DECL + val;
  }

  private static String fmtFloat(String argName, float val, String expectation) {
    return MESSAGE_START + NAME_DECL + argName
            + SEP + TYPE_DECL + float.class.getSimpleName()
            + SEP + EXPECT_DECL + expectation
            + SEP + VAL_DECL + val;
  }

  private static String fmtString(String val, String expectation) {
    return MESSAGE_START + TYPE_DECL + String.class.getSimpleName()
            + SEP + EXPECT_DECL + expectation
            + SEP + VAL_DECL + val;
  }

  private static String fmtInt(int val, String expectation) {
    return MESSAGE_START + TYPE_DECL + int.class.getSimpleName()
            + SEP + EXPECT_DECL + expectation
            + SEP + VAL_DECL + val;
  }

  private static String fmtLong(long val, String expectation) {
    return MESSAGE_START + TYPE_DECL + long.class.getSimpleName()
            + SEP + EXPECT_DECL + expectation
            + SEP + VAL_DECL + val;
  }

  private static String fmtShort(short val, String expectation) {
    return MESSAGE_START + TYPE_DECL + short.class.getSimpleName()
            + SEP + EXPECT_DECL + expectation
            + SEP + VAL_DECL + val;
  }

  private static String fmtDouble(double val, String expectation) {
    return MESSAGE_START + TYPE_DECL + double.class.getSimpleName()
            + SEP + EXPECT_DECL + expectation
            + SEP + VAL_DECL + val;
  }

  private static String fmtFloat(float val, String expectation) {
    return MESSAGE_START + TYPE_DECL + float.class.getSimpleName()
            + SEP + EXPECT_DECL + expectation
            + SEP + VAL_DECL + val;
  }

  private Ensure() { }

}
