package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 0;
	int maxRetry = 2;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if (!result.isSuccess()) {
			if(count < maxRetry)
			{
				count++;
				result.setStatus(ITestResult.FAILURE);
				return true;
			} else {
				result.setStatus(ITestResult.FAILURE);
			}
		} else {
			result.setStatus(ITestResult.SUCCESS);
		}
		
		return false;
	}

}
