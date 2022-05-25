# SimpleValidityChecker

This is a simple validity checker that adds validity checks and runs them on data. Currently 3 checks exists:
* NotNullCheck - checks if data is not null
* PersonalNumberCheck - checks if the data is a valid Swedish personal number.
* CarRegistrationNumberCheck - checks if the data is a valid "normal" car registration number.

## How to run
1. Create a an instance of the ValidityChecker and name the job in the constructor.
2. Add the test you want to run in that job to that instance using the addChecks()-method.
3. Run the data you want to check trough the job using the runChecks()-method.

## Tests
There are some unit tests included, but they are nowhere near fully covered.
