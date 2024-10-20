using PPAI.Gestor;
using PPAI.Recursos_Extra;

namespace PPAI
{
    internal static class Program
    {
        /// <summary>
        ///  The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            // To customize application configuration such as set high DPI settings or default font,
            // see https://aka.ms/applicationconfiguration.
            ApplicationConfiguration.Initialize();
            CargaDeDatosAlSistema.RecuperarDatosDeJSONs();
            Application.Run(new PantallaImportarActualizacionBodegas());
        }
    }
}