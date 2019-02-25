import { AppPage } from './app.po';
import { browser, by } from 'protractor';

describe('News App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
    browser.driver.manage().window().maximize();
  });

  
  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('NewsApp');
  });

  it('should be redirected to /login route on opening application ',() =>{
    browser.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/login')
  });

  // it(' Should be redirected to /register route on clicking Register button ', () => {
  //   browser.sleep(2000);
  //    browser.element(by.id('register-button')).click()
  //    expect(browser.getCurrentUrl()).toContain('/register')
  // })

  // it(' Should ablre to Register User', () => {
  //   browser.sleep(1000);
  //   browser.element(by.id('username')).sendKeys('user-username');
  //   browser.element(by.id('password')).sendKeys('user-password');
  //   browser.element(by.id('firstname')).sendKeys('user-firstname');
  //   browser.element(by.id('lastname')).sendKeys('user-lastname');
  //   browser.element(by.id('register-btn')).click()
  //   expect(browser.getCurrentUrl()).toContain('/login')
  // });

  
  it(' Should able to login', () => {
    browser.sleep(1000);
    browser.element(by.id('username')).sendKeys('admin');
    browser.element(by.id('password')).sendKeys('admin');
    browser.element(by.id('login-user')).click();
    expect(browser.getCurrentUrl()).toContain('/home');
  })

  it('Should add news into watchList', () => {
    browser.sleep(1000);
    browser.element(by.id('adminaddBtn')).click();
  })

  it('should go to category to add news', () => {
    browser.sleep(1000);
    browser.element(by.id('navbarDropdown')).click();
    browser.element(by.id('navbarDropdownBusiness')).click();
    expect(browser.getCurrentUrl()).toContain('category/business');
  })
  
  it('should Redirect to WatchList', () => {
    browser.sleep(1000);
    browser.element(by.id('watch-list')).click()
    expect(browser.getCurrentUrl()).toContain('/watchlist');
  })

  it('Should update news from watchList', () => {
    browser.sleep(5000);
    browser.element(by.id('updateBtn')).click();
    expect(browser.getCurrentUrl()).toContain('/news');
  })

   it('should able to delete news from watchList', () => {
     browser.sleep(2000);
     browser.element(by.id('watch-list')).click()
     browser.element(by.id('deleteBtn')).click();
   })

  it('should able to logout',()=>{
    browser.sleep(1000);
    browser.element(by.id('log-out')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  })
  
});
