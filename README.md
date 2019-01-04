# View Test Rule

Test `View`s in isolation.

## Download

```gradle
androidTestImplementation 'com.21buttons:view-test-rule:1.0.0'
debugImplementation 'com.21buttons:view-test-rule-extras:1.0.0'
```

## Usage

```java
@Rule
public ViewTestRule<?, TextView> viewTestRule =
  ViewTestRule.create((context, parent) -> {
      final TextView textView = new TextView(context);
      textView.setId(R.id.v_for_view);
      return textView;
  });

@Test
public void checkEmpty() throws Throwable {
  onView(withId(R.id.v_for_view)).check(matches(withText("")));
}
```

You can check the sample code for more examples.
