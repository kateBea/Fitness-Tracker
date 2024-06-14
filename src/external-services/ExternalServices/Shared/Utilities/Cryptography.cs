using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace Shared.Utilities
{
    /// <summary>
    /// Utilidades de criptografía
    /// </summary>
    public static class Cryptography
    {
        #region Properties

        /// <summary>
        /// Modulo AES
        /// </summary>
        private static readonly Aes _aes = Aes.Create();

        #endregion

        /// <summary>
        /// Encrypts a string to bytes using AES (Advanced Encryption Standard).
        /// </summary>
        /// <param name="plainText">The plain text to encrypt.</param>
        /// <param name="Key">The AES encryption key. If not provided, a key will be internally generated.</param>
        /// <param name="IV">The initialization vector (IV) used by the AES algorithm. If not provided, an IV will be internally generated.</param>
        /// <param name="aes">Optional AES object to customize encryption settings like mode of operation and padding.</param>
        /// <returns>Encrypted bytes of the input plain text.</returns>
        /// <exception cref="ArgumentNullException">Thrown when <paramref name="plainText"/> is null.</exception>
        /// <exception cref="ArgumentNullException">Thrown when <paramref name="Key"/> is null or has zero length.</exception>
        /// <exception cref="ArgumentNullException">Thrown when <paramref name="IV"/> is null or has zero length.</exception>
        public static byte[] EncryptStringToBytes_Aes(string plainText, byte[]? Key = null, byte[]? IV = null, Aes? aes = null)
        {
            Key = Key is null ? _aes.Key : null;
            IV = IV is null ? _aes.IV : null;
            aes = aes is null ? _aes : null;

            // Check arguments.
            if (plainText == null || plainText.Length <= 0)
                throw new ArgumentNullException("Null or empty crypt data");
            if (Key?.Length <= 0)
                throw new ArgumentNullException("empty key");
            if (IV?.Length <= 0)
                throw new ArgumentNullException("empty IV");

            byte[] encrypted;

            // Create an Aes object
            // with the specified key and IV.
            {
                aes.Key = Key;
                aes.IV = IV;

                // Create an encryptor to perform the stream transform.
                ICryptoTransform encryptor = aes.CreateEncryptor(aes.Key, aes.IV);

                // Create the streams used for encryption.
                using (MemoryStream msEncrypt = new MemoryStream())
                {
                    using (CryptoStream csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write))
                    {
                        using (StreamWriter swEncrypt = new StreamWriter(csEncrypt))
                        {
                            //Write all data to the stream.
                            swEncrypt.Write(plainText);
                        }
                        encrypted = msEncrypt.ToArray();
                    }
                }
            }

            // Return the encrypted bytes from the memory stream.
            return encrypted;
        }

        /// <summary>
        /// Decrypts bytes to a string using AES (Advanced Encryption Standard).
        /// </summary>
        /// <param name="cipherText">The encrypted bytes to decrypt.</param>
        /// <param name="Key">The AES decryption key. If not provided, the same key used for encryption should be used.</param>
        /// <param name="IV">The initialization vector (IV) used by the AES algorithm. If not provided, the same IV used for encryption should be used.</param>
        /// <param name="aes">Optional AES object to customize decryption settings like mode of operation and padding.</param>
        /// <returns>The decrypted plain text.</returns>
        /// <exception cref="ArgumentNullException">Thrown when <paramref name="cipherText"/> is null or has zero length.</exception>
        /// <exception cref="ArgumentNullException">Thrown when <paramref name="Key"/> is null or has zero length.</exception>
        /// <exception cref="ArgumentNullException">Thrown when <paramref name="IV"/> is null or has zero length.</exception>
        /// <exception cref="CryptographicException">Thrown when decryption fails, typically due to invalid key or IV.</exception>
        public static string DecryptStringFromBytes_Aes(byte[] cipherText, byte[]? Key = null, byte[]? IV = null, Aes? aes = null)
        {
            Key = Key is null ? _aes.Key : null;
            IV = IV is null ? _aes.IV : null;
            aes = aes is null ? _aes : null;

            // Check arguments.
            if (cipherText == null || cipherText.Length <= 0)
                throw new ArgumentNullException("Null or empty cipher text");
            if (Key?.Length <= 0)
                throw new ArgumentNullException("empty key");
            if (IV?.Length <= 0)
                throw new ArgumentNullException("empty IV");

            // Declare the string used to hold
            // the decrypted text.
            string plaintext = null;

            // Create an Aes object
            // with the specified key and IV.
            {
                aes.Key = Key;
                aes.IV = IV;

                // Create a decryptor to perform the stream transform.
                ICryptoTransform decryptor = aes.CreateDecryptor(aes.Key, aes.IV);

                // Create the streams used for decryption.
                using (MemoryStream msDecrypt = new MemoryStream(cipherText))
                {
                    using (CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read))
                    {
                        using (StreamReader srDecrypt = new StreamReader(csDecrypt))
                        {

                            // Read the decrypted bytes from the decrypting stream
                            // and place them in a string.
                            plaintext = srDecrypt.ReadToEnd();
                        }
                    }
                }
            }

            return plaintext;
        }
    }
}
