const request = require("supertest");
const app = require("./app"); // Make sure the path to app.js is correct

describe("App routing", () => {

    test("GET request to root should return 200", async () => {
        await request(app).get("/").expect(200);
    });

    test("Pet submission should return a 200 status", async () => {
        let petData = {
            petname: "Rusty",
            age: 5,
            specie: "dog",
            gender: "male",
            remark: "Friendly dog"
        };
        await request(app).post("/submit").send(petData).expect(200);
    });

    // Assuming there are more routes to test, you'd add them here.

    // Also, based on the example you provided, I'm adding the test for /myaction route:
    describe("passing data to /myaction", () => {
        test("post has proper response", async () => {
            let data = { 
                color: "green", 
                mynumber: 7
            };
            await request(app).post("/myaction").send(data).expect(200);
            data.color = undefined;
            await request(app).post("/myaction").send(data).expect(422);
        });
    });
});
