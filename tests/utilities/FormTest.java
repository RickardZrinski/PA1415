package tests.utilities;

import org.junit.*;
import utilities.Form;
import tests.utilities.mock.FormTestObject;
import tests.utilities.mock.FormTestView;

import static org.junit.Assert.assertEquals;

/**
 * @author  Dino Opijac
 * @since   14/05/14
 */
public class FormTest {
    private FormTestView view = new FormTestView();
    private FormTestObject object;
    private Form form;
    private static FormTest instance;

    public FormTest() {
        this.view.addComboBoxItem("A");
        this.view.addComboBoxItem("B");
        this.view.addComboBoxItem("C");

        this.view.setPasswordField("*** 1 2 3 ***");
        this.view.setTextArea("This is a very long text. Usually posted inside a textarea.");
        this.view.setTextField("A textfield");
        this.view.setIntTextField(2500);
        this.view.setFloatTextField(3.141549f);
        this.view.setDoubleTextField(2.50);
        this.view.setCharTextField('f');
    }

    @BeforeClass
    public static void start() {
        instance = new FormTest();
    }

    @AfterClass
    public static void finish() {
        // Make sure we dispose the frame
        instance.view.getFrame().dispose();
    }

    @Before
    public void setUp() throws Exception {
        this.object = new FormTestObject();
        this.form   = new Form(this.view);

        // Fill the object with values from the form
        this.form.fill(object);
    }

    @After
    public void tearDown() throws Exception {
        // Both these operations are only used to free memory
        // from testing
        this.object = null; // Destroy the object
        this.form = null;   // Destroy the form
    }

    @Test
    public void testComboBox() throws Exception {
        assertEquals("Selected item in combo box does not match", this.view.getComboBoxText(), this.object.getComboBox());
    }

    @Test
    public void testPasswordField() throws Exception {
        assertEquals("PasswordField A != B", this.view.getPasswordFieldText(), this.object.getPasswordField());
    }

    @Test
    public void testTextArea() throws Exception {
        assertEquals("TextArea A != B", this.view.getTextAreaText(), this.object.getTextArea());
    }

    @Test
    public void testTextField() throws Exception {
        assertEquals("TextField A != B", this.view.getTextFieldText(), this.object.getTextField());
    }

    @Test
    public void testIntTextField() throws Exception {
        assertEquals("Int TextField A != B", Integer.parseInt(this.view.getIntTextFieldText()), this.object.getIntTextField());
    }

    @Test
    public void testFloatTextField() throws Exception {
        assertEquals("Float TextField A != B", Float.parseFloat(this.view.getFloatTextFieldText()), this.object.getFloatTextField(), 5);
    }

    @Test
    public void testDoubleTextField() throws Exception {
        assertEquals("Double TextField A != B", Double.parseDouble(this.view.getDoubleTextFieldText()), this.object.getDoubleTextField(), 3);
    }

    @Test
    public void testCharTextField() throws Exception {
        assertEquals("Char TextField A != B", this.view.getCharTextFieldText().charAt(0), this.object.getCharTextField());
    }
}
