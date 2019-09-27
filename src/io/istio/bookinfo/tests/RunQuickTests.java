package io.istio.bookinfo.tests;

import org.junit.runners.Suite;
import io.istio.bookinfo.groups.GroupQuickTest;
import io.istio.bookinfo.repo.tests.ProductPage01Tests;
import io.istio.bookinfo.repo.tests.Ratings03Tests;
import io.istio.bookinfo.repo.tests.Reviews02Tests;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

@RunWith(Categories.class)
@Categories.IncludeCategory(GroupQuickTest.class)
@Suite.SuiteClasses({ProductPage01Tests.class, Reviews02Tests.class, Ratings03Tests.class})
public class RunQuickTests{

}


