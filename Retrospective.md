During Sprint 1, the Planetarium Web Application demonstrated solid progress in building core 
functionalities such as adding moons to valid planet IDs, implementing cascade delete when a 
planet is removed, and ensuring proper UI updates when actions are taken. Navigation flows were 
smooth, with successful redirects and page loads upon completing major operations. The system consistently 
reflected user actions, particularly in the planet and moon tables, confirming that backend synchronization 
with the frontend was handled well. Overall, the application's fundamental workflows performed reliably when
valid data was provided, contributing to a positive user experience for standard use cases.

However, several critical issues emerged during acceptance testing that need immediate attention. 
Validation errors, especially around user registration and file uploads, were not handled gracefully, leading to user confusion and gaps in feedback. Found lacking, allowing improper entries like whitespace-only names to be accepted. These defects indicate a need for more robust client-side validation, better error messaging, and stricter form input checks in the next sprint. 
Strengthening these areas will enhance the system’s resilience to bad data, improve user clarity, and ensure higher quality releases moving forward.